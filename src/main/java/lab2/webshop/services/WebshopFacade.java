package lab2.webshop.services;

import lab2.webshop.openapi.model.ProductEntity;

import java.util.List;

public interface WebshopFacade {
    List<ProductEntity> getAllProducts();
    ProductEntity getOneProduct(String productId);
}
