package storeLab.gateway_service.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import storeLab.gateway_service.entity.User;
import storeLab.gateway_service.service.OrderService;
import storeLab.gateway_service.service.ProductsService;
import storeLab.gateway_service.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final ProductsService productsService;
    private final UserService userService;

    public OrderController(OrderService orderService, ProductsService productsService, UserService userService) {
        this.orderService = orderService;
        this.productsService = productsService;
        this.userService = userService;
    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam Integer productId, @RequestParam String username){
        orderService.addToCart(username, productId);
        return "redirect:/menu";
    }

    @GetMapping("/cart/{username}")
    public String getCart(@PathVariable String username, Model model){
        model.addAttribute("carts", orderService.getCart(username));
        model.addAttribute("product", productsService);
        model.addAttribute("user", userService.getUserByUsername(username));
        return "cart";
    }

    @GetMapping("/orderAccess")
    public String orderAccess(@RequestParam MultiValueMap<String, String> map, Model model){
        orderService.changeCart(map);
        model.addAttribute("user", userService.getUserByUsername(map.get("username").get(0)));
        model.addAttribute("carts", orderService.getCart(map.get("username").get(0)));
        model.addAttribute("product", productsService);
        model.addAttribute("price", orderService.getFullPrice(map.get("username").get(0))-Integer.parseInt(map.get("discount").get(0)));
        model.addAttribute("discount", map.get("discount").get(0));
        return "createOrder";
    }

    @GetMapping("/createOrder")
    public String createOrder(@RequestParam MultiValueMap<String, String> map, Model model){
        model.addAttribute("order", orderService.createOrder(map));
        return "message";
    }

    @GetMapping("/getCheck/{filename}")
    public void getCheck(@PathVariable String filename, HttpServletResponse response){
        Path file = Paths.get("C:\\Users\\uhovs\\IdeaProjects\\gateway_service\\src\\main\\resources\\static\\files", filename);
        if(Files.exists(file)){
            response.setHeader("Content-disposition", "attachment;filename=" + filename);
            response.setContentType("application/pdf");
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/deleteCart/{id}")
    public String deleteFromCart(@PathVariable Integer id, @AuthenticationPrincipal User user){
        orderService.deleteCart(id);
        return "redirect:/cart/"+user.getUsername();
    }

    @GetMapping("/getStatistic")
    public String getStatistic(Model model){
        model.addAttribute("count", orderService.getCountOrders());
        model.addAttribute("price", orderService.getAllPrice());
        model.addAttribute("countByDate", orderService.getOrderCountByDate());
        return "statistic";
    }
}
