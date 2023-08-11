package lab2.webshop.document;

import lab2.webshop.openapi.model.Product;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document("products")
public class ProductItem extends Product {

    public ProductItem(String productId, String name, BigDecimal price, String description, String image){
        super(productId, name, price, description, image);
    }
}
