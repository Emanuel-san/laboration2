package lab2.webshop.services;

import lab2.webshop.openapi.model.ProductEntity;

public interface BackofficeFacade {

    ProductEntity addProduct(ProductEntity productToAdd);
}
