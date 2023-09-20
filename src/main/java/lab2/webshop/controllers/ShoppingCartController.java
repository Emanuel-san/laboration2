package lab2.webshop.controllers;

import lab2.webshop.openapi.api.ShoppingCartsApi;
import lab2.webshop.openapi.model.*;
import lab2.webshop.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class ShoppingCartController implements ShoppingCartsApi {

    /** Service bean for all shopping cart related */
    ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService){
        this.shoppingCartService = shoppingCartService;
    }
    @Override
    public ResponseEntity<ShoppingCartEntity> createShoppingCart(String sessionId) {
        return ResponseEntity.ok(shoppingCartService.createShoppingCart(sessionId));
    }
    @Override
    public ResponseEntity<ShoppingCartEntity> getShoppingCart(String sessionId) {
        return ResponseEntity.ok(shoppingCartService.getShoppingCart(sessionId));
    }
    @Override
    public ResponseEntity<ShoppingCartEntity> addToShoppingCart(String sessionId, ProductEntity productEntity) {
        return ResponseEntity.ok(shoppingCartService.addProductToCart(productEntity, sessionId));
    }
    @Override
    public ResponseEntity<ShoppingCartEntity> deleteFromShoppingCart(String productId, String sessionId){
        return ResponseEntity.ok(shoppingCartService.deleteFromShoppingCart(productId, sessionId));
    }
}
