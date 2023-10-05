package lab2.webshop.services.implementation;

import lab2.webshop.controllers.ProductController;
import lab2.webshop.openapi.model.ProductEntity;
import lab2.webshop.services.BackofficeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BackofficeFacadeImpl implements BackofficeFacade {
    ProductController productController;
    @Autowired
    public BackofficeFacadeImpl(ProductController productController){
        this.productController = productController;
    }
    @Override
    public ProductEntity addProduct(ProductEntity productToAdd) {
        ResponseEntity<ProductEntity> response = productController.createProduct(productToAdd);
        return response.getBody();
    }
}