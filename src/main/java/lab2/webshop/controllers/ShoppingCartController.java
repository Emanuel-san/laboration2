package lab2.webshop.controllers;

import lab2.webshop.openapi.api.ShoppingCartsApi;
import lab2.webshop.openapi.model.CartSummary;
import lab2.webshop.openapi.model.ShoppingCart;
import lab2.webshop.openapi.model.ShoppingCartEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoppingCartController implements ShoppingCartsApi {

    @Override
    public ResponseEntity<CartSummary> createShoppingCart(ShoppingCartEntity shoppingCartEntity) {
        return ResponseEntity.ok(new CartSummary());
    }
    @Override
    public ResponseEntity<CartSummary> getShoppingCart(String productId) {
        System.out.println(productId);
        return ResponseEntity.ok(new CartSummary());
    }
    @Override
    public ResponseEntity<List<CartSummary>> getShoppingCarts() {
        return ResponseEntity.ok(new ArrayList<>());
    }
    @Override
    public ResponseEntity<CartSummary> updateShoppingCart(String shoppingCartId, ShoppingCart shoppingCart) {
        return ResponseEntity.ok(new CartSummary());
    }
}
