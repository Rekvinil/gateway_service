package storeLab.gateway_service.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import storeLab.gateway_service.entity.ProductCharacteristic;
import storeLab.gateway_service.entity.Role;
import storeLab.gateway_service.service.ProductsService;
import storeLab.gateway_service.service.ReviewService;
import storeLab.gateway_service.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserService userService;
    private final ProductsService productsService;
    private final ReviewService reviewService;

    public AdminController(UserService userService, ProductsService productsService, ReviewService reviewService) {
        this.userService = userService;
        this.productsService = productsService;
        this.reviewService = reviewService;
    }

    @GetMapping("/adminPage")
    public String adminPage(Model model){
        model.addAttribute("reviews", reviewService.getReviewsOp());
        model.addAttribute("products", productsService);
        return "adminPage";
    }

    @GetMapping("/user")
    public String getUserList(Model model){
        model.addAttribute("users", userService.getUsers());
        return "userList";
    }

    @GetMapping("/user/{username}")
    public String userEdit(@PathVariable String username, Model model){
        model.addAttribute("user", userService.getUserByUsername(username));
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping("/user")
    public String userSave(@RequestParam String username, @RequestParam Map<String,String> form){
        userService.changeRoleOfUser(username,form);
        return "redirect:/user";
    }

    @GetMapping("/changeProduct/{product}")
    public String changeProduct(@PathVariable Integer product, Model model){
        model.addAttribute("product", productsService.getProduct(product));
        List<ProductCharacteristic> productCharacteristics = productsService.getCharacteristicsOfProduct(product);
        model.addAttribute("productscharacteristics", productCharacteristics);
        return "changeProduct";
    }

    @PostMapping("/changeProduct")
    public String changeProduct(@RequestParam Map<String, String> requestParams,
                                @RequestParam("img") MultipartFile img) throws Exception{
        productsService.changeProduct(requestParams, img);
        return "redirect:/getProducts";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam Map<String,String> requestParams,
                             @RequestParam("img") MultipartFile img) throws IOException {
        productsService.addProduct(requestParams, img);
        return "redirect:/getProducts";
    }

    @GetMapping("/addProduct")
    public String getProductAddPage(Model model){
        model.addAttribute("characteristics", productsService.getCharacteristics());
        return "addProduct";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Integer id){
        productsService.deleteProduct(id);
        return "redirect:/getProducts";
    }

    @GetMapping("/getProducts")
    public String getProducts(Model model){
        model.addAttribute("products",productsService.getProducts());
        return "getProducts";
    }

    @GetMapping("/addDiscount")
    public String getPageOfDiscount(){
        return "addDiscount";
    }

    @PostMapping("/addDiscount")
    public String addDiscount(@RequestParam MultiValueMap<String, String> params){
        productsService.addDiscount(params);
        return "redirect:/adminPage";
    }

    @GetMapping("/deleteReview/{review}")
    public String deleteReview(@PathVariable Integer review){
        reviewService.deleteReview(review);
        return "redirect:/adminPage";
    }
}
