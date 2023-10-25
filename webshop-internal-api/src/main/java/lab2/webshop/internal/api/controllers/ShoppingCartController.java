package lab2.webshop.internal.api.controllers;

import lab2.webshop.internal.api.services.ShoppingCartService;
import lab2.webshop.openapi.api.ShoppingCartsApi;
import lab2.webshop.openapi.model.ProductEntity;
import lab2.webshop.openapi.model.ShoppingCartEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class ShoppingCartController implements ShoppingCartsApi {

    /** Service for all shopping cart related */
    ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService){
        this.shoppingCartService = shoppingCartService;
    }
    @Override
    public ResponseEntity<ShoppingCartEntity> createShoppingCart(String cartId) {
        return ResponseEntity.ok(shoppingCartService.createShoppingCart(cartId));
    }
    @Override
    public ResponseEntity<ShoppingCartEntity> getShoppingCart(String cartId) {
        return ResponseEntity.ok(shoppingCartService.getShoppingCart(cartId));
    }
    @Override
    public ResponseEntity<ShoppingCartEntity> addToShoppingCart(String cartId, ProductEntity productEntity) {
        return ResponseEntity.ok(shoppingCartService.addProductToCart(productEntity, cartId));
    }
    @Override
    public ResponseEntity<ShoppingCartEntity> deleteFromShoppingCart(String productId, String cartId){
        return ResponseEntity.ok(shoppingCartService.deleteFromShoppingCart(productId, cartId));
    }
}
