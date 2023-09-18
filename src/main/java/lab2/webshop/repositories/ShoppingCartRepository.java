package lab2.webshop.repositories;

import lab2.webshop.openapi.model.ShoppingCartEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCartEntity, String>, CustomShoppingCartRepository {

    @Query("{sessionId:'?0'}")
    ShoppingCartEntity findCartBySessionId(final String sessionId);
}
