package lab2.webshop.internal.api.repositories;

import lab2.webshop.openapi.model.Product;

/**
 * Interface to create custom queries for a collection.
 * Products is the target collection in this case.
 */
public interface CustomProductRepository {

    /**
     * Update an existing product.
     * @param productId product id to update.
     * @param product contains which product fields to update.
     */
    void updateProduct(final String productId, final Product product);
}
