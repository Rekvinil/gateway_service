package storeLab.gateway_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import storeLab.gateway_service.entity.Characteristic;
import storeLab.gateway_service.service.ProductsService;

import java.util.List;

@Controller
public class CharacteristicsController {
    private final ProductsService productsService;

    public CharacteristicsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/getCharacteristics")
    public String getCharacteristics(Model model){
        List<Characteristic> characteristics = productsService.getCharacteristics();
        model.addAttribute("characteristics", characteristics);
        return "getCharacteristics";
    }

    @PostMapping("/addCharacteristics")
    public String addCharacteristic(@RequestParam String name, @RequestParam String description){
        productsService.addCharacteristic(name, description);
        return "redirect:/getCharacteristics";
    }

    @GetMapping("/addCharacteristics")
    public String addCharacteristics(){
        return "addCharacteristic";
    }

    @GetMapping("/changeCharacteristic/{characteristic}")
    public String changeCharacteristic(@PathVariable Integer characteristic, Model model){
        model.addAttribute("characteristic", productsService.getCharacteristic(characteristic));
        return "changeCharacteristic";
    }

    @PostMapping("/changeCharacteristic")
    public String change(@RequestParam Integer id, @RequestParam String name, @RequestParam String description){
        productsService.changeCharacteristic(id,name,description);
        return "redirect:/getCharacteristics";
    }

    @GetMapping("/deleteCharacteristic/{id}")
    public String delete(@PathVariable Integer id){
        productsService.deleteCharacteristic(id);
        return "redirect:/getCharacteristics";
    }

}
