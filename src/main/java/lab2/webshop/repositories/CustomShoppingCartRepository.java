package lab2.webshop.repositories;

import lab2.webshop.openapi.model.CartItem;
import lab2.webshop.openapi.model.ShoppingCartEntity;

public interface CustomShoppingCartRepository {

    ShoppingCartEntity pushCartItem(CartItem item, String sessionId);
}
