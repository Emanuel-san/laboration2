package lab2.webshop.services;

import lab2.webshop.openapi.model.ProductEntity;
import lab2.webshop.openapi.model.ShoppingCart;
import lab2.webshop.openapi.model.User;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;

public interface WebshopFacade {
    List<ProductEntity> getAllProducts();
    ProductEntity getOneProduct(String productId);
    ShoppingCart getShoppingCart(String sessionId);
    ShoppingCart addToCart(String productId, String sessionId);
    ShoppingCart deleteFromCart(String productId, String sessionId);
    User getUser(OAuth2User principal);
    User addUserOnFirstLogin(DefaultOidcUser user);
}
