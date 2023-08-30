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

    final ProductRepository productRepository;
    final Validator validator;
    public ProductsController(ProductRepository productRepository, Validator validator){
        this.productRepository = productRepository;
        this.validator = validator;
    }
    @Override
    public ResponseEntity<List<ProductEntity>> getProducts(String name){
        return ResponseEntity.ok(new ArrayList<>(productRepository.findAll()));
    }

    @Override
    public ResponseEntity<ProductEntity> createProduct(ProductEntity productEntity){
        productRepository.insert(productEntity);
        return ResponseEntity.ok(productEntity);
    }

    @Override
    public ResponseEntity<ProductEntity> getProduct(String productId){
        final ProductEntity productEntity = productRepository.findItemByProductId(productId);
        if(productEntity == null) {
            throw new ProductNotFoundException(productId);
        }
        return ResponseEntity.ok(productEntity);
    }

    @Override
    public ResponseEntity<ProductEntity> updateProduct(String productId, Product product){
        productRepository.updateProduct(productId, product);
        final ProductEntity productEntity = productRepository.findItemByProductId(productId);
        return ResponseEntity.ok(productEntity);
    }

    @Override
    public ResponseEntity<ProductEntity> deleteProduct(String productId){
        ProductEntity productEntity = productRepository.deleteItemByProductId(productId);
        if(productEntity == null) {
            throw new ProductNotFoundException(productId);
        }
        return ResponseEntity.ok(productEntity);
    }
}