package lab2.webshop.repositories.implementation;

import com.mongodb.BasicDBObject;
import lab2.webshop.openapi.model.CartItem;
import lab2.webshop.openapi.model.ShoppingCartEntity;
import lab2.webshop.repositories.CustomShoppingCartRepository;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * Implementation of {@link CustomShoppingCartRepository}
 */
public class CustomShoppingCartRepositoryImpl implements CustomShoppingCartRepository{

    /** Template to query database {@link MongoTemplate}*/
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
        ShoppingCartEntity shoppingCartEntity = mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true), ShoppingCartEntity.class);
        if(shoppingCartEntity == null) {
            shoppingCartEntity = mongoTemplate.findOne(new Query(Criteria.where("sessionId").is(sessionId)), ShoppingCartEntity.class);
        }
        return shoppingCartEntity;
    }

    @Override
    public ShoppingCartEntity removeCartItem(String productId, String sessionId) {
        Query query = new Query(Criteria.where("sessionId").is(sessionId));
        Update update = new Update().pull("productItems", new BasicDBObject("productId", productId));
        return mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true), ShoppingCartEntity.class);
    }
}