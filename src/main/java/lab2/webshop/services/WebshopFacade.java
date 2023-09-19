package lab2.webshop.services;

import lab2.webshop.openapi.model.ProductEntity;
import lab2.webshop.openapi.model.ShoppingCart;

import java.util.List;

public interface WebshopFacade {
    List<ProductEntity> getAllProducts();
    ProductEntity getOneProduct(String productId);
    ShoppingCart getShoppingCart(String sessionId);
    ShoppingCart addToCart(String productId, String sessionId);
}
