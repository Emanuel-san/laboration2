package lab2.webshop.interfaces;

import lab2.webshop.document.ProductItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ProductRepository extends MongoRepository<ProductItem, String> {

    @Query("{name:'?0'}")
    ProductItem findItemByName(String name);
}
