package lab2.webshop.internal.api.services.implementation;

import lab2.webshop.internal.api.repositories.ShoppingCartRepository;
import lab2.webshop.internal.api.services.ShoppingCartService;
import lab2.webshop.openapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Default implementation of {@link ShoppingCartService}
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository){
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public ShoppingCartEntity createShoppingCart(String cartId) {
        final ShoppingCartEntity newCart = new ShoppingCartEntity(cartId);
        newCart.setProductItems(new ArrayList<>());
        shoppingCartRepository.insert(newCart);
        return newCart;
    }

    @Override
    public ShoppingCartEntity getShoppingCart(String cartId) {
        ShoppingCartEntity shoppingCartEntity = shoppingCartRepository.findCartByCartId(cartId);
        if(shoppingCartEntity == null) {
            // Was not able to find a cart associated to the session id, create a new one.
            shoppingCartEntity = createShoppingCart(cartId);
        }
        return shoppingCartEntity;
    }

    @Override
    public ShoppingCartEntity addProductToCart(ProductEntity productEntity, String cartId) {
        CartItem newItem = new CartItem(productEntity.getProductId());
        Product product = Product.builder()
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .image(Optional.ofNullable(productEntity).map(ProductEntity::getImage).orElse(null))
                .build();
        newItem.setQuantity(new BigDecimal(1));
        newItem.setProduct(product);
        ShoppingCartEntity shoppingCartEntity = shoppingCartRepository.pushCartItem(newItem, cartId);
        if(shoppingCartEntity == null){
            // Was not able to find a cart associated to the cart id, create a new one.
            shoppingCartEntity = createShoppingCart(cartId);
        }
        return shoppingCartEntity;
    }

    @Override
    public ShoppingCartEntity deleteFromShoppingCart(String productId, String cartId) {
        return shoppingCartRepository.removeCartItem(productId, cartId);
    }
}
