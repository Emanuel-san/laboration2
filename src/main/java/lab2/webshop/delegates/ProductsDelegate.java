package lab2.webshop.delegates;

import lab2.webshop.document.ProductItem;
import lab2.webshop.openapi.api.ProductsApi;
import lab2.webshop.openapi.api.ProductsApiDelegate;
import lab2.webshop.openapi.model.ErrorResponse;
import lab2.webshop.openapi.model.Product;
import lab2.webshop.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductsDelegate implements ProductsApiDelegate {

    ProductRepository productItemRepo;
    public ProductsDelegate(ProductRepository productItemRepo){
        this.productItemRepo = productItemRepo;
    }
    @Override
    public ResponseEntity<List<Product>> getProducts(String name){
        return ResponseEntity.ok(productItemRepo.findAll());
    }

    @Override
    public ResponseEntity<Product> createProduct(Product product){
       return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<Product> getProduct(String productId){
        Product product = productItemRepo.findItemByProductId(productId);
        if(product == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(product);
    }
}