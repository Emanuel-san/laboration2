package lab2.webshop.repositories;

import lab2.webshop.openapi.model.Product;
import org.springframework.data.mongodb.core.MongoTemplate;

public class CustomShoppingCartRepositoryImpl implements CustomShoppingCartRepository{

    final MongoTemplate mongoTemplate;

    public CustomShoppingCartRepositoryImpl(final MongoTemplate template){
        this.mongoTemplate = template;
    }
    @Override
    public void pushProduct(Product product) {


    }
}
