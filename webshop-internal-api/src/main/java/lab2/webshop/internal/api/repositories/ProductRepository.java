package lab2.webshop.internal.api.repositories;

import lab2.webshop.openapi.model.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for products collection.
 */
@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String>, CustomProductRepository {
    /**
     * Find product by ID
     * @param productId product id
     * @return {@link ProductEntity}
     */
    ProductEntity findItemByProductId(final String productId);

    /**
     * Delete product by id
     * @param productId product id
     * @return {@link ProductEntity} deleted product
     */
    @Query(value = "{productId:'?0'}", delete = true)
    ProductEntity deleteItemByProductId(final String productId);
}
