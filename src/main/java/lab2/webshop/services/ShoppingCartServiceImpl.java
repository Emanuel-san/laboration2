package lab2.webshop.services;

import lab2.webshop.exceptions.NotFoundException;
import lab2.webshop.openapi.model.CartSummary;
import lab2.webshop.openapi.model.ShoppingCart;
import lab2.webshop.openapi.model.ShoppingCartEntity;
import lab2.webshop.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository){
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public ShoppingCartEntity createShoppingCart() {
        final ShoppingCartEntity newCart = new ShoppingCartEntity(UUID.randomUUID().toString());
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
}
