package storeLab.gateway_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import storeLab.gateway_service.entity.Product;
import storeLab.gateway_service.entity.ProductCharacteristic;
import storeLab.gateway_service.service.ProductsService;

import java.util.List;
import java.util.Map;

@Controller
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/getProducts")
    public String getProducts(Model model){
        model.addAttribute("products",productsService.getProducts());
        return "getProducts";
    }

    @GetMapping("/changeProduct/{product}")
    public String getProduct(@PathVariable Integer product, Model model){
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
    public String addProduct(@RequestParam Map<String,String> requestParams){
        productsService.addProduct(requestParams);
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
