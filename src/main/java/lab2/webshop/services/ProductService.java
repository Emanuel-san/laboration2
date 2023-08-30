package lab2.webshop.services;

import lab2.webshop.openapi.model.Product;
import lab2.webshop.openapi.model.ProductEntity;

import java.util.List;
public interface ProductService {
    List<ProductEntity> getProducts(String name);
    List<ProductEntity> getProducts();
    ProductEntity createProduct(ProductEntity productEntity);
    ProductEntity getProduct(String productId);
    ProductEntity updateProduct(String productId, Product product);
    ProductEntity deleteProduct(String productId);
}
