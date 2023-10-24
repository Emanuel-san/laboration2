package lab2.webshop.internal.api.repositories;

import lab2.webshop.openapi.model.Order;
import lab2.webshop.openapi.model.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    @Query("{orderId:'?0'}")
    ProductEntity findOrderByOrderId(final String orderId);
}
