package lab2.webshop.repository;

import lab2.webshop.document.ProductItem;
import lab2.webshop.openapi.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<ProductItem, String> {

    @Query("{productId:'?0'}")
    ProductItem findItemByProductId(String productId);

}
