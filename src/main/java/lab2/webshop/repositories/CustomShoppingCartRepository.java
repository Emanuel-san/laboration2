package lab2.webshop.repositories;

import lab2.webshop.openapi.model.Product;

public interface CustomShoppingCartRepository {

    void pushProduct(Product product);
}
