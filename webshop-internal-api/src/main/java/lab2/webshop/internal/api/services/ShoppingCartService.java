package lab2.webshop.internal.api.services;

import lab2.webshop.openapi.model.CartSummary;
import lab2.webshop.openapi.model.ProductEntity;
import lab2.webshop.openapi.model.ShoppingCart;
import lab2.webshop.openapi.model.ShoppingCartEntity;

import java.util.List;

public interface ShoppingCartService {

    ShoppingCartEntity createShoppingCart(String cartId);
    ShoppingCartEntity getShoppingCart(String cartId);
    List<CartSummary> getShoppingCarts();
    CartSummary updateShoppingCart(String shoppingCartId, ShoppingCart shoppingCart);
    ShoppingCartEntity addProductToCart(ProductEntity productEntity, String cartId);
    ShoppingCartEntity deleteFromShoppingCart(String productId, String cartId);
}
