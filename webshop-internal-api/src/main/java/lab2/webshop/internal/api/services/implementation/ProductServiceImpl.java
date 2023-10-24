package lab2.webshop.internal.api.services.implementation;

import lab2.webshop.internal.api.exceptions.NotFoundException;
import lab2.webshop.internal.api.repositories.ProductRepository;
import lab2.webshop.internal.api.services.ProductService;
import lab2.webshop.openapi.model.Product;
import lab2.webshop.openapi.model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    final ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductEntity> getProducts(String name) {
        return new ArrayList<>(productRepository.findAll());
    }

    @Override
    public ProductEntity createProduct(ProductEntity productEntity) {
        return productRepository.insert(productEntity);
    }

    @Override
    public ProductEntity getProduct(String productId) {
        final ProductEntity productEntity = productRepository.findItemByProductId(productId);
        if(productEntity == null) {
            throw new NotFoundException("No product found with ID: " + productId);
        }
        return productEntity;
    }

    @Override
    public ProductEntity updateProduct(String productId, Product product) {
        productRepository.updateProduct(productId, product);
        return productRepository.findItemByProductId(productId);
    }

    @Override
    public ProductEntity deleteProduct(String productId) {
        ProductEntity productEntity = productRepository.deleteItemByProductId(productId);
        if(productEntity == null) {
            throw new NotFoundException("No product found with ID: " + productId);
        }
        return productEntity;
    }
}
