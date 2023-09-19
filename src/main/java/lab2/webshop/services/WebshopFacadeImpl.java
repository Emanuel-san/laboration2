package lab2.webshop.services;

import lab2.webshop.controllers.OrderController;
import lab2.webshop.controllers.ProductController;
import lab2.webshop.controllers.ShoppingCartController;
import lab2.webshop.exceptions.NotFoundException;
import lab2.webshop.openapi.model.ProductEntity;
import lab2.webshop.openapi.model.ShoppingCart;
import lab2.webshop.openapi.model.ShoppingCartEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebshopFacadeImpl implements WebshopFacade {
    final ProductController productController;
    final ShoppingCartController shoppingCartController;
    final OrderController orderController;

    @Autowired
    public WebshopFacadeImpl(ProductController productController,
                             ShoppingCartController shoppingCartController,
                             OrderController orderController){
        this.productController = productController;
        this.shoppingCartController = shoppingCartController;
        this.orderController = orderController;
    }
    @Override
    public List<ProductEntity> getAllProducts() {
        final ResponseEntity<List<ProductEntity>> responseList = productController.getProducts(null);
        return responseList.getBody();
    }
    @Override
    public ProductEntity getOneProduct(final String productId){
        final ResponseEntity<ProductEntity> response = productController.getProduct(productId);
        return response.getBody();
    }

    @Override
    public ShoppingCart getShoppingCart(final String sessionId) {
        final ResponseEntity<ShoppingCartEntity> response = shoppingCartController.getShoppingCart(sessionId);
        final ShoppingCartEntity shoppingCartEntity = response.getBody();
        final ShoppingCart cart = new ShoppingCart();
        if(shoppingCartEntity == null) {
            // Initiate an empty list if we for some reason fail to collect/create a cart for the session.
            cart.setProductItems(new ArrayList<>());
            return cart;
        }
        cart.setProductItems(shoppingCartEntity.getProductItems());
        return cart;
    }

    @Override
    public ShoppingCart addToCart(final String productId, final String sessionId) {
        final ProductEntity productEntity = productController.getProduct(productId).getBody();
        final ShoppingCartEntity shoppingCartEntity = shoppingCartController.addToShoppingCart(sessionId, productEntity).getBody();
        ShoppingCart shoppingCart = new ShoppingCart();
        if (shoppingCartEntity == null || shoppingCartEntity.getProductItems().isEmpty()){
            // Something went wrong adding to cart, return a new empty cart.
            shoppingCart.setProductItems(new ArrayList<>());
            return shoppingCart;
        }
        shoppingCart.setProductItems(shoppingCartEntity.getProductItems());
        return shoppingCart;
    }
}
