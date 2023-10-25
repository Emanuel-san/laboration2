package lab2.webshop.web.services;

import lab2.webshop.openapi.model.ProductEntity;
import lab2.webshop.openapi.model.ShoppingCart;
import lab2.webshop.openapi.model.User;
import lab2.webshop.web.controllers.WebshopController;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;

/**
 * Facade for {@link WebshopController}
 */
public interface WebshopFacade {
    List<ProductEntity> getAllProducts();
    ProductEntity getOneProduct(String productId);
    ShoppingCart getShoppingCart(String cartId);
    ShoppingCart addToCart(String productId, String cartId);
    ShoppingCart deleteFromCart(String productId, String cartId);
    User getUser(OAuth2User principal);
    User isFirstTimeLogin(DefaultOidcUser user);
}
