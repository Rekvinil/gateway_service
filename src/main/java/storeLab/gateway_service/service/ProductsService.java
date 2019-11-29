package storeLab.gateway_service.service;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import storeLab.gateway_service.entity.Product;


import java.util.Arrays;
import java.util.List;
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
        restTemplate.postForObject(URL_PRODUCT+"/productEditing/changeProduct",requestBody,Product.class);
    }

    public void addProduct(String name, String price, String discount, String img){
        float pricef = Float.parseFloat(price);
        Product product = new Product(name, pricef, discount, img);
        HttpEntity<Product> requestBody = new HttpEntity<>(product);
        restTemplate.postForObject(URL_PRODUCT+"/productEditing/addProduct", requestBody, Product.class);
    }
}
