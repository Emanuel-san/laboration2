package lab2.webshop.repositories.implementation;

import com.mongodb.client.result.UpdateResult;
import lab2.webshop.exceptions.NotFoundException;
import lab2.webshop.openapi.model.Product;
import lab2.webshop.openapi.model.ProductEntity;
import lab2.webshop.repositories.CustomProductRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@link CustomProductRepository}
 */
public class CustomProductRepositoryImpl implements CustomProductRepository {

    /** Template to query database {@link MongoTemplate}*/
    final MongoTemplate mongoTemplate;

    /** Track number of fields to update in a product */
    int keyCounter;
    public CustomProductRepositoryImpl(final MongoTemplate template){
        this.mongoTemplate = template;
    }

    @Override
    public void updateProduct(final String productId, final Product product) {
        final Query query = new Query(Criteria.where("productId").is(productId));
        final Update update = new Update();
        final Map<String, Object> fieldsToUpdate = mapObjectFields(product);
        keyCounter = 0;
        fieldsToUpdate.forEach((key, value) -> {
            if (value != null) {
                update.set(key, value);
                keyCounter++;
            }
        });

        if(keyCounter > 0) {
            final UpdateResult result = mongoTemplate.updateFirst(query, update, ProductEntity.class);
            if (result.getMatchedCount() == 0) {
                throw new NotFoundException("No product found with ID: " + productId);
            }
        }
    }

    /**
     * Map product fields.
     * @param product contains product fields to update.
     * @return mapped product fields.
     */
    private Map<String, Object> mapObjectFields(final Product product){
        final Map<String, Object> objectFields = new HashMap<>();
        objectFields.put("name", product.getName());
        objectFields.put("price", product.getPrice());
        objectFields.put("description", product.getDescription());
        objectFields.put("image", product.getImage());
        return objectFields;
    }
}
