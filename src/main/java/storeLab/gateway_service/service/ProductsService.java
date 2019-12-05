package storeLab.gateway_service.service;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import storeLab.gateway_service.entity.Characteristic;
import storeLab.gateway_service.entity.Product;
import storeLab.gateway_service.entity.ProductCharacteristic;


import java.util.ArrayList;
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
        for(ProductCharacteristic pc : pcs){
            System.out.println(pc);
        }
        ProductCharacteristic[] productCharacteristics = new ProductCharacteristic[pcs.size()];
        HttpEntity<ProductCharacteristic[]> requestPut = new HttpEntity<>(pcs.toArray(productCharacteristics));
        restTemplate.put(URL_PRODUCT+"/productEditing/changeCharacteristicsOfProduct", requestPut, ProductCharacteristic[].class);
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

    public void deleteProduct(Integer id){
        restTemplate.delete(URL_PRODUCT+"/productEditing/deleteProduct/"+id);
    }

    public List<ProductCharacteristic> getCharacteristicsOfProduct(Integer id){
        ProductCharacteristic[] productCharacteristics =
                restTemplate.getForObject(URL_PRODUCT+"/productEditing/getCharacteristicsOfProduct/"+id,ProductCharacteristic[].class);
        return Arrays.asList(productCharacteristics);
    }
}
