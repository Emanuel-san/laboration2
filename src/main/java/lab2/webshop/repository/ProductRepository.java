package lab2.webshop.repository;

import lab2.webshop.openapi.model.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String>, CustomProductRepository {

    @Query("{productId:'?0'}")
    ProductEntity findItemByProductId(final String productId);
}
