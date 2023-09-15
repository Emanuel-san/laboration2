package lab2.webshop.repositories;

import lab2.webshop.openapi.model.CartItem;
import lab2.webshop.openapi.model.ShoppingCartEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class CustomShoppingCartRepositoryImpl implements CustomShoppingCartRepository{

    final MongoTemplate mongoTemplate;

    public CustomShoppingCartRepositoryImpl(final MongoTemplate template){
        this.mongoTemplate = template;
    }
    @Override
    public ShoppingCartEntity pushCartItem(CartItem item, String sessionId) {
        // Query to select shopping cart with matching session id push to document if product id does not already exist.
        Query query = new Query(
                Criteria.where("sessionId")
                        .is(sessionId)
                        .and("productItems.productId")
                        .ne(item.getProductId()));
        Update update = new Update().push("productItems", item);
        return mongoTemplate.findAndModify(query, update, ShoppingCartEntity.class);
    }
}
