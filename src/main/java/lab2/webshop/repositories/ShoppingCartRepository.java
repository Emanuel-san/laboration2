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
     * Fetch a shopping cart by sessionId.
     * @param sessionId current session id.
     * @return {@link ShoppingCartEntity}
     */
    @Query("{sessionId:'?0'}")
    ShoppingCartEntity findCartBySessionId(final String sessionId);
}
