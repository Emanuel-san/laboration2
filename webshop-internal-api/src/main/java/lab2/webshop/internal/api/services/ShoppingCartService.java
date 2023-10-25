package lab2.webshop.internal.api.services;

import lab2.webshop.openapi.model.ProductEntity;
import lab2.webshop.openapi.model.ShoppingCartEntity;
/**
 * Service interface for carts
 */
public interface ShoppingCartService {
    /**
     * Add a new shopping cart to database
     * @param cartId new UUID
     * @return created {@link ShoppingCartEntity}
     */
    ShoppingCartEntity createShoppingCart(String cartId);

    /**
     * Fetch a single cart by id
     * @param cartId cart id
     * @return {@link ShoppingCartEntity}
     */
    ShoppingCartEntity getShoppingCart(String cartId);

    /**
     * Add a product to a cart
     * @param productEntity {@link ProductEntity}
     * @param cartId cart id
     * @return updated cart
     */
    ShoppingCartEntity addProductToCart(ProductEntity productEntity, String cartId);
    /**
     * Delete a product from a cart by product id
     * @param productId product id
     * @param cartId cart id
     * @return updated cart
     */
    ShoppingCartEntity deleteFromShoppingCart(String productId, String cartId);
}
