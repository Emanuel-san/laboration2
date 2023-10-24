package lab2.webshop.web.services;

import lab2.webshop.openapi.model.ProductEntity;

public interface BackofficeFacade {

    ProductEntity addProduct(ProductEntity productToAdd);
    ProductEntity getProduct(String productId);
    ProductEntity updateProduct(ProductEntity productEntity);
    ProductEntity deleteProduct(String productId);
}
