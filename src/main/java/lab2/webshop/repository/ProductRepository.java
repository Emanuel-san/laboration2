package lab2.webshop.repository;

import lab2.webshop.openapi.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("{productId:'?0'}")
    Product findItemByProductId(String productId);
}
