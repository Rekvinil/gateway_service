package storeLab.gateway_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import storeLab.gateway_service.entity.ProductCharacteristic;
import storeLab.gateway_service.service.ProductsService;
import storeLab.gateway_service.service.ReviewService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ProductsController {

    private final ProductsService productsService;
    private final ReviewService reviewService;

    public ProductsController(ProductsService productsService, ReviewService reviewService) {
        this.productsService = productsService;
        this.reviewService = reviewService;
    }

    @GetMapping("/getProducts")
    public String getProducts(Model model){
        model.addAttribute("products",productsService.getProducts());
        return "getProducts";
    }

    @GetMapping("/menu/{product}")
    public String getProduct(@PathVariable Integer product,Model model){
        model.addAttribute("product", productsService.getProduct(product));
        model.addAttribute("description", productsService.getDescription(product));
        model.addAttribute("author", productsService.getAuthor(product));
        model.addAttribute("artist", productsService.getArtist(product));
        List<ProductCharacteristic> prch = productsService.getCharacteristicsOfProduct(product);
        List<ProductCharacteristic> characteristics = new ArrayList<>();
        for(ProductCharacteristic pr : prch){
            if(!(pr.getCharacteristics().getName().equals("Описание")||pr.getCharacteristics().getName().equals("Имя художника")
                    ||pr.getCharacteristics().getName().equals("Фамилия художника")
                    ||pr.getCharacteristics().getName().equals("Имя сценариста")
                    ||pr.getCharacteristics().getName().equals("Фамилия сценариста"))){
                characteristics.add(pr);
            }
        }
        model.addAttribute("characteristics", characteristics);
        model.addAttribute("reviews", reviewService.getReviewByProductId(product));
        return "productPage";
    }

    @PostMapping("/menu/addReview")
    public String addReview(@RequestParam String nameUser, @RequestParam Integer product,
                          @RequestParam String mark, @RequestParam String text, Model model){
        reviewService.addReview(nameUser, product, text, mark);

        return "redirect:/menu/"+product;
    }

    @GetMapping("/deleteReview/{product}/{review}")
    public String deleteReview(@PathVariable Integer review, @PathVariable Integer product){
        reviewService.deleteReview(review);
        return "redirect:/menu/"+ product;
    }

    @GetMapping("/changeProduct/{product}")
    public String changeProduct(@PathVariable Integer product, Model model){
        model.addAttribute("product", productsService.getProduct(product));
        List<ProductCharacteristic> productCharacteristics = productsService.getCharacteristicsOfProduct(product);
        model.addAttribute("productscharacteristics", productCharacteristics);
        return "changeProduct";
    }

    @PostMapping("/changeProduct")
    public String changeProduct(@RequestParam Map<String, String> requestParams){
        productsService.changeProduct(requestParams);
        return "redirect:/getProducts";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam Map<String,String> requestParams,
                             @RequestParam("img")MultipartFile img) throws IOException {
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

}
