package storeLab.gateway_service.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import storeLab.gateway_service.entity.Product;
import storeLab.gateway_service.entity.User;
import storeLab.gateway_service.service.OrderService;
import storeLab.gateway_service.service.ProductsService;

import java.util.Collections;


@Controller
public class MainController {
    private final ProductsService productsService;
    private final OrderService orderService;

    public MainController(ProductsService productsService, OrderService orderService) {
        this.productsService = productsService;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("products", productsService.getProductsWithDiscount());
        return "index";
    }

    @GetMapping("/menu")
    public String menu(Model model){
        model.addAttribute("products", productsService.getProducts());
        return "menu";
    }

    @GetMapping("/menu/DC")
    public String menuDC(Model model){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("Издательство", Collections.singletonList("DC"));
        model.addAttribute("products", productsService.getProductsByCharacteristic(params));
        return "menuDC";
    }

    @GetMapping("/menu/Marvel")
    public String menuMarvel(Model model){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("Издательство", Collections.singletonList("Marvel"));
        model.addAttribute("products", productsService.getProductsByCharacteristic(params));
        return "menuMarvel";
    }

    @GetMapping("/menu/Bubble")
    public String menuBubble(Model model){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("Издательство", Collections.singletonList("Bubble"));
        model.addAttribute("products", productsService.getProductsByCharacteristic(params));
        return "menuBubble";
    }

    @GetMapping("menu/filter")
    public String menuFilter(@RequestParam MultiValueMap<String,String> params, Model model){
        model.addAttribute("products", productsService.filter(params));
        return "menu";
    }

    @GetMapping("menu/Marvel/filter")
    public String menuFilterMarvel(@RequestParam MultiValueMap<String,String> params, Model model){
        model.addAttribute("products", productsService.filter(params));
        return "menuMarvel";
    }

    @GetMapping("menu/DC/filter")
    public String menuFilterDC(@RequestParam MultiValueMap<String,String> params, Model model){
        model.addAttribute("products", productsService.filter(params));
        return "menuDC";
    }

    @GetMapping("menu/Bubble/filter")
    public String menuFilterBubble(@RequestParam MultiValueMap<String,String> params, Model model){
        model.addAttribute("products", productsService.filter(params));
        return "menuBubble";
    }

    @GetMapping("menu/search")
    public String search(@RequestParam String name, Model model){
        model.addAttribute("products", productsService.searchByName(name));
        return "menu";
    }

    @GetMapping("menu/Marvel/search")
    public String searchMarvel(@RequestParam String name, Model model){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("Издательство", Collections.singletonList("Marvel"));
        model.addAttribute("products", productsService.searchFromList(params,name));
        return "menuMarvel";
    }

    @GetMapping("menu/DC/search")
    public String searchDC(@RequestParam String name, Model model){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("Издательство", Collections.singletonList("DC"));
        model.addAttribute("products", productsService.searchFromList(params,name));
        return "menuDC";
    }

    @GetMapping("menu/Bubble/search")
    public String searchBubble(@RequestParam String name, Model model){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("Издательство", Collections.singletonList("Bubble"));
        model.addAttribute("products", productsService.searchFromList(params,name));
        return "menuBubble";
    }

}
