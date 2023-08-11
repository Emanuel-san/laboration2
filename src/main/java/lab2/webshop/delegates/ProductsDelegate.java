package lab2.webshop.delegates;

import lab2.webshop.openapi.api.ProductsApiDelegate;
import lab2.webshop.openapi.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductsDelegate implements ProductsApiDelegate {

    public ProductsDelegate(){}

    @Override
    public ResponseEntity<List<Product>> getProducts(String name){
        List<Product> list = new ArrayList<>();
        Product prd = new Product("PRD-1", name, BigDecimal.valueOf(32.2), "A magical product", "imageurl");
        list.add(prd);

        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<Product> createProduct(Product product){
       return ResponseEntity.ok(product);
    }

}