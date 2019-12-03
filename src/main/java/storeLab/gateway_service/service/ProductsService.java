package storeLab.gateway_service.service;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import storeLab.gateway_service.entity.Characteristic;
import storeLab.gateway_service.entity.Product;
import storeLab.gateway_service.entity.ProductCharacteristic;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    private final String URL_PRODUCT = "http://localhost:8081";
    private RestTemplate restTemplate = new RestTemplate();

    public List<Product> getProducts(){
        Product[] products = restTemplate.getForObject(URL_PRODUCT+"/productEditing/getProducts",Product[].class);
        return Arrays.stream(products).collect(Collectors.toList());
    }

    public Product getProduct(Integer id){
        return restTemplate.getForObject(URL_PRODUCT+"/productEditing/"+id,Product.class);
    }

    public void changeProduct(String name, String price,
                              String discount, String img,
                              Integer id){
        float pricef = Float.parseFloat(price);
        Product product = new Product(id,name,pricef,discount,img);
        HttpEntity<Product> requestBody = new HttpEntity<>(product);
        restTemplate.put(URL_PRODUCT+"/productEditing/changeProduct",requestBody,Product.class);
    }

    public void addProduct(Map<String,String> requestParams){
        float pricef = Float.parseFloat(requestParams.get("price"));
        Product product = new Product(requestParams.get("name"), pricef, requestParams.get("discount"), requestParams.get("img"));
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
}
