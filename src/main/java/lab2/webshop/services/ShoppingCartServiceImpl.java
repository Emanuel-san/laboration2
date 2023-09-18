package lab2.webshop.services;

import lab2.webshop.openapi.model.*;
import lab2.webshop.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository){
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public ShoppingCartEntity createShoppingCart(String sessionId) {
        final ShoppingCartEntity newCart = new ShoppingCartEntity(sessionId);
        newCart.setProductItems(new ArrayList<>());
        shoppingCartRepository.insert(newCart);
        return newCart;
    }

    @Override
    public ShoppingCartEntity getShoppingCart(String sessionId) {
        ShoppingCartEntity shoppingCartEntity = shoppingCartRepository.findCartBySessionId(sessionId);
        if(shoppingCartEntity == null) {
            // Was not able to find a cart associated to the session id, create a new one.
            shoppingCartEntity = createShoppingCart(sessionId);
        }
        return shoppingCartEntity;
    }

    @Override
    public List<CartSummary> getShoppingCarts() {
        // TODO - remove?
        return null;
    }

    @Override
    public CartSummary updateShoppingCart(String shoppingCartId, ShoppingCart shoppingCart) {
        // TODO - see controller
        return null;
    }

    @Override
    public ShoppingCartEntity addProductToCart(ProductEntity productEntity, String sessionId) {
        CartItem newItem = new CartItem(productEntity.getProductId());
        Product product = Product.builder()
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .image(Optional.ofNullable(productEntity).map(ProductEntity::getImage).orElse(null))
                .build();
        newItem.setQuantity(new BigDecimal(1));
        newItem.setProduct(product);
        ShoppingCartEntity shoppingCartEntity = shoppingCartRepository.pushCartItem(newItem, sessionId);
        if(shoppingCartEntity == null){
            // Was not able to find a cart associated to the session id, create a new one.
            shoppingCartEntity = createShoppingCart(sessionId);
        }
        return shoppingCartEntity;
    }
}
