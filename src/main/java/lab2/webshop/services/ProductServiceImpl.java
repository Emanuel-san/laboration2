package lab2.webshop.services;

import lab2.webshop.exceptions.ProductNotFoundException;
import lab2.webshop.openapi.model.Product;
import lab2.webshop.openapi.model.ProductEntity;
import lab2.webshop.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductEntity> getProducts(String name) {
        return new ArrayList<>(productRepository.findAll());
    }

    @Override
    public List<ProductEntity> getProducts() {
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
            throw new ProductNotFoundException(productId);
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
            throw new ProductNotFoundException(productId);
        }
        return productEntity;
    }
}
