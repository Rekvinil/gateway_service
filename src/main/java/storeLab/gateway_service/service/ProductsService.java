package storeLab.gateway_service.service;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import storeLab.gateway_service.entity.Characteristic;
import storeLab.gateway_service.entity.Product;
import storeLab.gateway_service.entity.ProductCharacteristic;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    @Value("${upload.path}")
    private String uploadPath;
    private final String URL_PRODUCT = "http://localhost:8081";
    private RestTemplate restTemplate = new RestTemplate();

    public List<Product> getProducts(){
        Product[] products = restTemplate.getForObject(URL_PRODUCT+"/productEditing/getProducts",Product[].class);
        return Arrays.stream(products).collect(Collectors.toList());
    }

    public Product getProduct(Integer id){
        return restTemplate.getForObject(URL_PRODUCT+"/productEditing/"+id,Product.class);
    }

    public void changeProduct(Map<String, String> requestParams){
        float pricef = Float.parseFloat(requestParams.get("price"));

        Product newProduct = new Product(Integer.parseInt(requestParams.get("id")),requestParams.get("name"),pricef,requestParams.get("discount"),
                requestParams.get("img"));
        HttpEntity<Product> requestBody = new HttpEntity<>(newProduct);
        restTemplate.put(URL_PRODUCT+"/productEditing/changeProduct",requestBody,Product.class);
        List<ProductCharacteristic> pcs = getCharacteristicsOfProduct(Integer.parseInt(requestParams.get("id")));
        for(ProductCharacteristic pc : pcs){
            pc.setValue(requestParams.get(pc.getCharacteristics().getName()));
        }
        ProductCharacteristic[] productCharacteristics = new ProductCharacteristic[pcs.size()];
        HttpEntity<ProductCharacteristic[]> requestPut = new HttpEntity<>(pcs.toArray(productCharacteristics));
        restTemplate.put(URL_PRODUCT+"/productEditing/changeCharacteristicsOfProduct", requestPut, ProductCharacteristic[].class);
    }

    public List<Product> getProductsByCharacteristic(MultiValueMap<String, String> map){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_PRODUCT+"/productEditing/getProductsByCharacteristics")
                .queryParams(map);
        Product[] products = restTemplate.getForObject(builder.toUriString(),Product[].class);
        return Arrays.stream(products).collect(Collectors.toList());
    }

    public void addProduct(Map<String,String> requestParams, MultipartFile img) throws IOException {
        String result="";
        if(img!=null){
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            result = uuidFile + "." + img.getOriginalFilename();
            img.transferTo(new File(uploadPath+"/"+result));
        }
        float pricef = Float.parseFloat(requestParams.get("price"));
        Product product = new Product(requestParams.get("name"), pricef, requestParams.get("discount"), result);
        HttpEntity<Product> requestBody = new HttpEntity<>(product);
        restTemplate.postForObject(URL_PRODUCT+"/productEditing/addProduct", requestBody, Product.class);
        List<Product> products = getProducts();
        List<Characteristic> characteristics = getCharacteristics();
        for(Product p : products){
            if(p.getName().equals(requestParams.get("name"))){
                for(Characteristic ch : characteristics){
                    addCharacteristicToProduct(p,ch,requestParams.get(ch.getId().toString()));
                }
            }
        }
    }

    void addCharacteristicToProduct(Product p, Characteristic ch, String value){
        ProductCharacteristic productCharacteristic = new ProductCharacteristic(p,ch,value);
        HttpEntity<ProductCharacteristic> requestBody = new HttpEntity<>(productCharacteristic);
        restTemplate.postForObject(URL_PRODUCT+"/productEditing/addProduct/addCharacteristic", requestBody, ProductCharacteristic.class);

    }

    public List<Characteristic> getCharacteristics(){
        Characteristic[] characteristics =
                restTemplate.getForObject(URL_PRODUCT+"/characteristicsEditing/getCharacteristics", Characteristic[].class);
        return Arrays.asList(characteristics);
    }

    public Characteristic getCharacteristic(Integer id){
        return restTemplate.getForObject(URL_PRODUCT+"/characteristicsEditing/getCharacteristics/"+id, Characteristic.class);
    }

    public void addCharacteristic(String name, String description){
        Characteristic characteristic = new Characteristic(name, description);
        HttpEntity<Characteristic> requestBody = new HttpEntity<>(characteristic);
        restTemplate.postForObject(URL_PRODUCT+"/characteristicsEditing/addCharacteristic", requestBody, Characteristic.class);
    }

    public void changeCharacteristic(Integer id,String name, String description){
        Characteristic characteristic = new Characteristic(id,name,description);
        HttpEntity<Characteristic> requestBody = new HttpEntity<>(characteristic);
        restTemplate.put(URL_PRODUCT+"/characteristicsEditing/characteristicEdit", requestBody, Characteristic.class);
    }

    public void deleteCharacteristic(Integer id){
        restTemplate.delete(URL_PRODUCT+"/characteristicsEditing/deleteCharacteristic/"+id);
    }

    public void deleteProduct(Integer id){
        restTemplate.delete(URL_PRODUCT+"/productEditing/deleteProduct/"+id);
    }

    public List<ProductCharacteristic> getCharacteristicsOfProduct(Integer id){
        ProductCharacteristic[] productCharacteristics =
                restTemplate.getForObject(URL_PRODUCT+"/productEditing/getCharacteristicsOfProduct/"+id,ProductCharacteristic[].class);
        return Arrays.asList(productCharacteristics);
    }

    public List<Product> filter(MultiValueMap<String,String> params){
        if(params.get("Страна").get(0).equals("Выбор")){
            params.remove("Страна");
        }
        List<Product> products = getProductsByCharacteristic(params);
        if(params.get("Цена").get(0).equals("больше")){
            products.sort((p1, p2) -> Float.compare(p1.getPrice(), p2.getPrice()));
        }
        if(params.get("Цена").get(0).equals("меньше")){
            products.sort((p1, p2) -> Float.compare(p2.getPrice(), p1.getPrice()));
        }
        if(params.get("Скидка").get(0).equals("да")){
            List<Product> productRes = new ArrayList<>();
            for (Product p : products){
                if(!p.getDiscount().equals("нет")){
                    productRes.add(p);
                }
            }
            return productRes;
        }
        return products;
    }

    public List<Product> searchByName(String name){

        List<Product> products = new ArrayList<>();
        for(Product pr : getProducts()){
            if(pr.getName().toLowerCase().startsWith(name.toLowerCase())){
                products.add(pr);
            }
        }
        return products;
    }

    public List<Product> searchFromList(MultiValueMap<String,String> params, String name){
        List<Product> products = new ArrayList<>();
        for(Product pr : getProductsByCharacteristic(params)){
            if(pr.getName().toLowerCase().startsWith(name.toLowerCase())){
                products.add(pr);
            }
        }
        return products;
    }

    public String getDescription(Integer id){
        for(ProductCharacteristic pc : getCharacteristicsOfProduct(id)){
            if(pc.getCharacteristics().getName().equals("Описание")){
                return pc.getValue();
            }
        }
        return "";
    }

    public String getAuthor(Integer id){
        StringBuilder s = new StringBuilder();
        for(ProductCharacteristic pc : getCharacteristicsOfProduct(id)){
            if(pc.getCharacteristics().getName().equals("Фамилия сценариста")
            || pc.getCharacteristics().getName().equals("Имя сценариста")){
                s.append(pc.getValue());
                s.append(" ");
            }
        }
        return s.toString();
    }


    public String getArtist(Integer id){
        StringBuilder s = new StringBuilder();
        for(ProductCharacteristic pc : getCharacteristicsOfProduct(id)){
            if(pc.getCharacteristics().getName().equals("Фамилия художника")
                    || pc.getCharacteristics().getName().equals("Имя художника")){
                s.append(pc.getValue());
                s.append(" ");
            }
        }
        return s.toString();
    }
}
