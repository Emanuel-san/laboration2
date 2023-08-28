package lab2.webshop.controllers;

import jakarta.validation.Validator;
import lab2.webshop.exception.ProductNotFoundException;

import lab2.webshop.openapi.api.ProductsApi;
import lab2.webshop.openapi.model.Product;
import lab2.webshop.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductsController implements ProductsApi {

    ProductRepository productItemRepo;
    Validator validator;
    public ProductsController(ProductRepository productItemRepo, Validator validator){
        this.productItemRepo = productItemRepo;
        this.validator = validator;
    }
    @Override
    public ResponseEntity<List<Product>> getProducts(String name){
        return ResponseEntity.ok(new ArrayList<>(productItemRepo.findAll()));
    }

    @Override
    public ResponseEntity<Product> createProduct(Product product){
        productItemRepo.insert(product);
        return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<Product> getProduct(String productId){
        final Product product = productItemRepo.findItemByProductId(productId);
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