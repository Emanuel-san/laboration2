package lab2.webshop.repositories;

import lab2.webshop.openapi.model.ShoppingCartEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for carts collection.
 */
@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCartEntity, String>, CustomShoppingCartRepository {

    /**
     * Fetch a shopping cart by cart id.
     * @param cartId cart id.
     * @return {@link ShoppingCartEntity}
     */
    @Query("{cartId:'?0'}")
    ShoppingCartEntity findCartByCartId(final String cartId);
}
