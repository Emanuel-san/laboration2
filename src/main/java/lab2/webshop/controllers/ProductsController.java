package lab2.webshop.controllers;

import jakarta.validation.Validator;

import lab2.webshop.exception.ProductNotFoundException;
import lab2.webshop.openapi.api.ProductsApi;
import lab2.webshop.openapi.model.Product;
import lab2.webshop.openapi.model.ProductEntity;
import lab2.webshop.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductsController implements ProductsApi {

    final ProductRepository productItemRepo;
    final Validator validator;
    public ProductsController(ProductRepository productItemRepo, Validator validator){
        this.productItemRepo = productItemRepo;
        this.validator = validator;
    }
    @Override
    public ResponseEntity<List<ProductEntity>> getProducts(String name){
        return ResponseEntity.ok(new ArrayList<>(productItemRepo.findAll()));
    }

    @Override
    public ResponseEntity<ProductEntity> createProduct(ProductEntity productEntity){
        productItemRepo.insert(productEntity);
        return ResponseEntity.ok(productEntity);
    }

    @Override
    public ResponseEntity<ProductEntity> getProduct(String productId){
        final ProductEntity productEntity = productItemRepo.findItemByProductId(productId);
        if(productEntity == null) {
            throw new ProductNotFoundException(productId);
        }
        return ResponseEntity.ok(productEntity);
    }

    @Override
    public ResponseEntity<ProductEntity> updateProduct(String productId, Product product){
        productItemRepo.updateProduct(productId, product);
        final ProductEntity productEntity = productItemRepo.findItemByProductId(productId);
        return ResponseEntity.ok(productEntity);
    }

//    @Override
//    public ResponseEntity<Product> deleteProduct(String productId){
//        return ResponseEntity.ok();
//    }
}