package lab2.webshop.internal.api.services;

import lab2.webshop.openapi.model.Product;
import lab2.webshop.openapi.model.ProductEntity;

import java.util.List;
/**
 * Service interface for products
 */
public interface ProductService {
    /**
     * Get all products or filter out by name
     * TODO filter not implemented
     * @param name filter string
     * @return list with {@link ProductEntity}
     */
    List<ProductEntity> getProducts(String name);

    /**
     * Add a new product to database
     * @param productEntity entity to add
     * @return added entity
     */
    ProductEntity createProduct(ProductEntity productEntity);

    /**
     * Fetch a single product by product id
     * @param productId product id
     * @return {@link ProductEntity}
     */
    ProductEntity getProduct(String productId);

    /**
     * Update informational data of a product
     * @param productId product id
     * @param product {@link Product}
     * @return entity with updated data
     */
    ProductEntity updateProduct(String productId, Product product);

    /**
     * Delete a product by id
     * @param productId product id
     * @return deleted entity
     */
    ProductEntity deleteProduct(String productId);
}
