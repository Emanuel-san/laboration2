package lab2.webshop.delegates;

import lab2.webshop.exception.ProductNotFoundException;
import lab2.webshop.openapi.api.ProductsApiDelegate;
import lab2.webshop.openapi.model.Product;
import lab2.webshop.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

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
        return ResponseEntity.ok(new ArrayList<>(productItemRepo.findAll()));
    }

    @Override
    public ResponseEntity<Product> createProduct(Product product){
       return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<Product> getProduct(String productId){
        Product product = productItemRepo.findItemByProductId(productId);
        if(product == null) {
            throw new ProductNotFoundException(productId);
        }
        return ResponseEntity.ok(product);
    }

//    @Override
//    public ResponseEntity<Product> deleteProduct(String productId){
//        return ResponseEntity.ok();
//    }
}