package lab2.webshop.services;

import lab2.webshop.controllers.ProductController;
import lab2.webshop.openapi.model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebshopFacadeImpl implements WebshopFacade {
    final ProductController productController;

    @Autowired
    public WebshopFacadeImpl(ProductController productController){
        this.productController = productController;
    }
    @Override
    public List<ProductEntity> getAllProducts() {
        ResponseEntity<List<ProductEntity>> responseList = productController.getProducts(null);
        return responseList.getBody();
    }
    @Override
    public ProductEntity getOneProduct(String productId){
        ResponseEntity<ProductEntity> response = productController.getProduct(productId);
        return response.getBody();
    }
}
