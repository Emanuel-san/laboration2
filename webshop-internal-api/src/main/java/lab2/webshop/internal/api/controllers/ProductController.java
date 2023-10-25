package lab2.webshop.internal.api.controllers;

import jakarta.validation.Validator;
import lab2.webshop.internal.api.services.ProductService;
import lab2.webshop.openapi.api.ProductsApi;
import lab2.webshop.openapi.model.Product;
import lab2.webshop.openapi.model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class ProductController implements ProductsApi {

    /** Service for all products related */
    final ProductService productService;

    /** Validates objects created from incoming requests */
    final Validator validator;
    @Autowired
    public ProductController(ProductService productService, Validator validator){
        this.productService = productService;
        this.validator = validator;
    }
    @Override
    public ResponseEntity<List<ProductEntity>> getProducts(String name){
        return ResponseEntity.ok(productService.getProducts(name));
    }
    @Override
    public ResponseEntity<ProductEntity> createProduct(ProductEntity productEntity){
        return ResponseEntity.ok(productService.createProduct(productEntity));
    }
    @Override
    public ResponseEntity<ProductEntity> getProduct(String productId){
        return ResponseEntity.ok(productService.getProduct(productId));
    }
    @Override
    public ResponseEntity<ProductEntity> updateProduct(String productId, Product product){
        return ResponseEntity.ok(productService.updateProduct(productId, product));
    }
    @Override
    public ResponseEntity<ProductEntity> deleteProduct(String productId){
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }
}