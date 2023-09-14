package lab2.webshop.controllers;

import lab2.webshop.openapi.api.ShoppingCartsApi;
import lab2.webshop.openapi.model.CartSummary;
import lab2.webshop.openapi.model.ShoppingCart;
import lab2.webshop.openapi.model.ShoppingCartEntity;
import lab2.webshop.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoppingCartController implements ShoppingCartsApi {

    /** Service bean for all shopping cart related */
    ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService){
        this.shoppingCartService = shoppingCartService;
    }
    @Override
    public ResponseEntity<ShoppingCartEntity> createShoppingCart() {
        return ResponseEntity.ok(shoppingCartService.createShoppingCart());
    }
    @Override
    public ResponseEntity<ShoppingCartEntity> getShoppingCart(String shoppingCartId) {
        return ResponseEntity.ok(shoppingCartService.getShoppingCart(shoppingCartId));
    }
    @Override
    public ResponseEntity<List<ShoppingCartEntity>> getShoppingCarts() {
        return ResponseEntity.ok(new ArrayList<>());
    }
    @Override
    public ResponseEntity<CartSummary> updateShoppingCart(String shoppingCartId, ShoppingCart shoppingCart) {
        // TODO - How to handle this logic? Should it just be add product?
        return ResponseEntity.ok(new CartSummary());
    }
}
