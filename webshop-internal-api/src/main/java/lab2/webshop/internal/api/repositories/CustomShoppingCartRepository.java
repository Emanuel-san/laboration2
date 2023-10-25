package lab2.webshop.internal.api.repositories;

import lab2.webshop.openapi.model.CartItem;
import lab2.webshop.openapi.model.ShoppingCartEntity;

/**
 * Interface to create custom queries for a collection.
 * Carts is the target collection in this case.
 */
public interface CustomShoppingCartRepository {

    /**
     * Push an item to a shopping cart.
     * @param item represents a product in the cart
     * @param cartId cart id.
     * @return {@link ShoppingCartEntity}
     */
    ShoppingCartEntity pushCartItem(CartItem item, String cartId);

    /**
     * Remove an item from a cart
     * @param productId product id
     * @param cartId cart id
     * @return {@link ShoppingCartEntity}
     */
    ShoppingCartEntity removeCartItem(String productId, String cartId);
}
