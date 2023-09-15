package lab2.webshop.services;

import lab2.webshop.exceptions.NotFoundException;
import lab2.webshop.openapi.model.*;
import lab2.webshop.repositories.ShoppingCartRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public ShoppingCartEntity getShoppingCart(String shoppingCartId) {
        ShoppingCartEntity cart = shoppingCartRepository.findCartByCartId(shoppingCartId);
        if(cart == null) {
            throw new NotFoundException("Cart with id " + shoppingCartId + " was not found");
        }
        return cart;
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
    public boolean addProductToCart(ProductEntity productEntity, String session) {
        CartItem newItem = new CartItem(productEntity.getProductId());
        Product product = Product.builder()
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .image(Optional.ofNullable(productEntity).map(ProductEntity::getImage).orElse(null))
                .build();
        newItem.setQuantity(new BigDecimal(1));
        newItem.setProduct(product);
        ShoppingCartEntity shoppingCartEntity = shoppingCartRepository.pushCartItem(newItem, session);
        return shoppingCartEntity != null;
    }
}
