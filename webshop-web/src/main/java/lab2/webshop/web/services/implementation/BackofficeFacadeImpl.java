package lab2.webshop.web.services.implementation;

import lab2.webshop.openapi.api.ProductsApi;
import lab2.webshop.openapi.model.Product;
import lab2.webshop.openapi.model.ProductEntity;
import lab2.webshop.web.services.BackofficeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackofficeFacadeImpl implements BackofficeFacade {
    final ProductsApi productsApi;

    @Autowired
    public BackofficeFacadeImpl(ProductsApi productsApi){
        this.productsApi = productsApi;
    }
    @Override
    public ProductEntity addProduct(ProductEntity productToAdd) {
        return productsApi.createProduct(productToAdd);
    }

    @Override
    public ProductEntity getProduct(String productId) {
        return productsApi.getProduct(productId);
    }

    @Override
    public ProductEntity updateProduct(ProductEntity productEntity) {
        Product product = Product.builder()
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .description(productEntity.getDescription())
                .image(productEntity.getImage())
                .build();
        return productsApi.updateProduct(productEntity.getProductId(), product);
    }

    @Override
    public ProductEntity deleteProduct(String productId) {
        return productsApi.deleteProduct(productId);
    }
}
