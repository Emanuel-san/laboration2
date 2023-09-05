package lab2.webshop.services;

import lab2.webshop.openapi.model.CartSummary;
import lab2.webshop.openapi.model.ShoppingCart;
import lab2.webshop.openapi.model.ShoppingCartEntity;

import java.util.List;

public interface ShoppingCartService {

    ShoppingCartEntity createShoppingCart();
    ShoppingCartEntity getShoppingCart(String shoppingCartId);
    List<CartSummary> getShoppingCarts();
    CartSummary updateShoppingCart(String shoppingCartId, ShoppingCart shoppingCart);
}
