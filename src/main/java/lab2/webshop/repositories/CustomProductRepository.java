package lab2.webshop.repositories;

import lab2.webshop.openapi.model.Product;

public interface CustomProductRepository {
    void updateProduct(final String productId, final Product product);
}
