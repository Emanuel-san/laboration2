package lab2.webshop.services;

import lab2.webshop.openapi.model.CartSummary;
import lab2.webshop.openapi.model.ProductEntity;
import lab2.webshop.openapi.model.ShoppingCart;
import lab2.webshop.openapi.model.ShoppingCartEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShoppingCartService {

    ShoppingCartEntity createShoppingCart(String sessionId);
    ShoppingCartEntity getShoppingCart(String sessionId);
    List<CartSummary> getShoppingCarts();
    CartSummary updateShoppingCart(String shoppingCartId, ShoppingCart shoppingCart);
    ShoppingCartEntity addProductToCart(ProductEntity productEntity, String session);
    ShoppingCartEntity deleteFromShoppingCart(String productId, String sessionId);
}
