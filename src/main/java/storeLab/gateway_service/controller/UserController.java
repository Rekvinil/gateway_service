package storeLab.gateway_service.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import storeLab.gateway_service.entity.Product;
import storeLab.gateway_service.entity.Review;
import storeLab.gateway_service.entity.Role;
import storeLab.gateway_service.entity.User;
import storeLab.gateway_service.service.ProductsService;
import storeLab.gateway_service.service.ReviewService;
import storeLab.gateway_service.service.UserService;
import storeLab.gateway_service.service.WishListService;

import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    private final UserService userService;
    private final ReviewService reviewService;
    private final ProductsService productsService;
    private final WishListService wishListService;

    public UserController(UserService userService, ReviewService reviewService, ProductsService productsService, WishListService wishListService) {
        this.userService = userService;
        this.reviewService = reviewService;
        this.productsService = productsService;
        this.wishListService = wishListService;
    }

    @GetMapping("/profile/{username}")
    public String getProfile(@PathVariable String username, Model model){
        model.addAttribute("users", userService.getUserByUsername(username));
        List<Review> reviews = reviewService.getReviewByUser(userService.getUserByUsername(username));
        model.addAttribute("reviews", reviews);
        List<Product> products = productsService.getProducts();
        model.addAttribute("products", productsService);
        model.addAttribute("wishlists", wishListService.getWishList(userService.getUserByUsername(username)));
        return "profile";
    }

    @GetMapping("/profileEditing/{username}")
    public String profileEditing(@PathVariable String username, Model model){
        model.addAttribute("users", userService.getUserByUsername(username));
        return "profileEditing";
    }

    @PostMapping("/profileEditing/")
    public String profileEditingStart(User user){
        userService.userEditing(user);
        return "redirect:/profileEditing/" + user.getUsername();
    }

    @PostMapping("/addToWishList")
    public String addToWishList(@RequestParam Integer productId, @RequestParam String username){
        wishListService.addToWishList(userService.getUserByUsername(username),productId);
        return "redirect:/menu";
    }
}
