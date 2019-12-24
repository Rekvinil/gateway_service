package storeLab.gateway_service.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import storeLab.gateway_service.entity.*;
import storeLab.gateway_service.repository.CartRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductsService productsService;
    private RestTemplate restTemplate = new RestTemplate();
    private final String URL_ORDER = "http://localhost:8082";

    public OrderService(CartRepository cartRepository, UserService userService, ProductsService productsService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productsService = productsService;
    }

    public void addToCart(String username, Integer productId){
        if(cartRepository.findByUserAndProductId(userService.getUserByUsername(username), productId)!=null){
            Cart cart = cartRepository.findByUserAndProductId(userService.getUserByUsername(username), productId);
            cart.setCount(cart.getCount()+1);
            cartRepository.save(cart);
        }else{
            cartRepository.save(new Cart(userService.getUserByUsername(username),productId,1));
        }
    }

    public List<Cart> getCart(String username){
        User user = userService.getUserByUsername(username);
        if(cartRepository.findAllByUser(user)==null){
            return new ArrayList<>();
        }
        else return cartRepository.findAllByUser(user);
    }


    public void changeCart(MultiValueMap<String,String> map){
        for(int i = 0; i<map.get("productId").size(); i++){
            Cart cart = cartRepository.findByUserAndProductId(userService.getUserByUsername(map.get("username").get(0)),
                    Integer.parseInt(map.get("productId").get(i)));
            cart.setCount(Integer.parseInt(map.get("count").get(i)));
            cartRepository.save(cart);
        }
    }

    public float getFullPrice(String username){
        float fullPrice = 0;
        List<Cart> carts = getCart(username);
        for(Cart cart : carts){
            fullPrice += productsService.getProduct(cart.getProductId()).getPrice()*cart.getCount()
                    *(1-Float.parseFloat(productsService.getProduct(cart.getProductId()).getDiscount())/100);
        }
        return  fullPrice;
    }

    public Order createOrder(MultiValueMap<String, String> map){
        List<CartForOrder> cart = new ArrayList<>();
        int discount = Integer.parseInt(map.get("discount").get(0));
        for(String s : map.get("cartId")){
            Cart cart1 = cartRepository.findById(Integer.parseInt(s)).orElse(null);
            Product p = productsService.getProduct(cart1.getProductId());
            p.setCount(p.getCount()-cart1.getCount());
            productsService.changeProductCount(p);
            CartForOrder cart2 = new CartForOrder(cart1.getId(), cart1.getUser().getId(), cart1.getProductId(), cart1.getCount(), discount);
            cart.add(cart2);
        }
        userService.desceasePoints(map.get("userId").get(0), Integer.parseInt(map.get("discount").get(0)));
        CartForOrder[] arr = new CartForOrder[cart.size()];
        cart.toArray(arr);
        HttpEntity<CartForOrder[]> body = new HttpEntity<>(arr);
        Integer orderId = restTemplate.postForObject(URL_ORDER+"/orderService/addOrder", body, Integer.class);
        Order order = restTemplate.getForObject(URL_ORDER+"/orderService/getOrder/"+orderId, Order.class);
        cartRepository.deleteAllByUser(userService.getUserByUsername(map.get("userId").get(0)));
        userService.changeProints(map.get("userId").get(0), (int)order.getPrice());
        createFile(order);
        return order;
    }

    private void createFile(Order order){
        File file = new File("C:\\Users\\uhovs\\IdeaProjects\\gateway_service\\src\\main\\resources\\static\\files\\"+order.getId().toString()+".pdf");
        User user = userService.getUserById(order.getUserId());
        final int FONT_SIZE_SMALL = 16;
        final int FONT_SIZE_BIG = 32;
        final int OFFSET = 40;
        ProductList[] productLists = restTemplate.getForObject(URL_ORDER+"/orderService/getProductList/"+order.getId(), ProductList[].class);
        try{
            Document document = new Document();
            BaseFont bf = BaseFont.createFont("C:\\Users\\uhovs\\IdeaProjects\\gateway_service\\src\\main\\resources\\static\\font\\tnr.ttf",
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font newFont = new Font(bf);
            PdfWriter.getInstance(document,
                    new FileOutputStream(file));
            document.open();

            Paragraph title = new Paragraph("Чек", newFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph("Информация о заказчике"));
            document.add(new Paragraph("Имя: " + user.getFirstName(),newFont ));
            document.add(new Paragraph("Фамилия: " + user.getLastName(),newFont ));
            document.add(new Paragraph("Город: " + user.getCity(),newFont ));
            document.add(new Paragraph("Адрес: " + user.getAdress(),newFont ));
            document.add(new Paragraph("Email: " + user.getEmail(),newFont ));
            document.add(new Paragraph("Информация о купленных товарах",newFont));
            for(ProductList pr : productLists){
                Product p = productsService.getProduct(pr.getProductId());
                document.add(new Paragraph("Наименование: "+p.getName()+"  Количество: "+pr.getCount(),newFont));
            }
            document.add(new Paragraph("Общая сумма(с учетом всех скидок): "+ order.getPrice(),newFont));
            document.add(new Paragraph("Дата покупки: "+order.getDate(),newFont));
            document.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void deleteCart(Integer id){
        cartRepository.deleteById(id);
    }

    public Order[] getAllOrders(){
        return restTemplate.getForObject(URL_ORDER+"/orderService/getAllOrders", Order[].class);
    }

    public int getCountOrders(){
        return getAllOrders().length;
    }

    public float getAllPrice(){
        float price = 0;
        for(Order order : getAllOrders()){
            price+=order.getPrice();
        }
        return price;
    }

    public int getOrderCountByDate(){
        int count=0;
        Date date = new Date();
        for(Order order : getAllOrders()){
            if(date.getMonth()==order.getDate().getMonth()){
                count++;
            }
        }
        return count;
    }
}
