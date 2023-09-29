package lab2.webshop.services.implementation;

import lab2.webshop.controllers.OrderController;
import lab2.webshop.controllers.ProductController;
import lab2.webshop.controllers.ShoppingCartController;
import lab2.webshop.controllers.UsersController;
import lab2.webshop.openapi.model.*;
import lab2.webshop.services.WebshopFacade;
import lab2.webshop.util.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebshopFacadeImpl implements WebshopFacade {
    final ProductController productController;
    final ShoppingCartController shoppingCartController;
    final OrderController orderController;
    final UsersController usersController;

    @Autowired
    public WebshopFacadeImpl(final ProductController productController,
                             final ShoppingCartController shoppingCartController,
                             final OrderController orderController,
                             final UsersController usersController){
        this.productController = productController;
        this.shoppingCartController = shoppingCartController;
        this.orderController = orderController;
        this.usersController = usersController;
    }
    @Override
    public List<ProductEntity> getAllProducts() {
        final ResponseEntity<List<ProductEntity>> responseList = productController.getProducts(null);
        return responseList.getBody();
    }
    @Override
    public ProductEntity getOneProduct(final String productId){
        final ResponseEntity<ProductEntity> response = productController.getProduct(productId);
        return response.getBody();
    }

    @Override
    public ShoppingCart getShoppingCart(final String sessionId) {
        final ResponseEntity<ShoppingCartEntity> response = shoppingCartController.getShoppingCart(sessionId);
        final ShoppingCartEntity shoppingCartEntity = response.getBody();
        return mapFromCartEntity(shoppingCartEntity);
    }

    @Override
    public ShoppingCart addToCart(final String productId, final String sessionId) {
        final ProductEntity productEntity = productController.getProduct(productId).getBody();
        final ShoppingCartEntity shoppingCartEntity = shoppingCartController.addToShoppingCart(sessionId, productEntity).getBody();
        return mapFromCartEntity(shoppingCartEntity);
    }

    @Override
    public ShoppingCart deleteFromCart(final String productId, final String sessionId) {
        final ShoppingCartEntity shoppingCartEntity = shoppingCartController.deleteFromShoppingCart(productId,sessionId).getBody();
        return mapFromCartEntity(shoppingCartEntity);
    }
    @Override
    public User getUser(DefaultOidcUser principal) {
        User user = mapUserFromOidc(principal);
        ResponseEntity<User> response = usersController.getUser(user.getProvider(), user.getEmail());
        return response.getBody();
    }

    @Override
    public User isClientAuthenticated(OAuth2User principal) {
        if(principal instanceof DefaultOidcUser user) {
            User shopUser = mapUserFromOidc(user);
            return usersController
                    .getUser(shopUser.getProvider(), user.getEmail()).getStatusCode()
                    .is2xxSuccessful() ? shopUser : usersController.addUser(shopUser).getBody();
        }
        return null;
    }

    private ShoppingCart mapFromCartEntity(final ShoppingCartEntity entity){
        final ShoppingCart cart = new ShoppingCart();
        if(entity == null) {
            // Something went wrong if entity is null, so we just initiate a new empty list.
            cart.setProductItems(new ArrayList<>());
            return cart;
        }
        cart.setProductItems(entity.getProductItems());
        return cart;
    }

    private User mapUserFromOidc(final DefaultOidcUser principal) {
        final User user = new User();
        if(principal.getIssuer().getAuthority().equals(Authorities.GOOGLE.getName())) {
            user.setProvider(Provider.GOOGLE);
        }
        else {
            user.setProvider(Provider.LOCAL);
        }
        user.setFirstname(principal.getGivenName());
        user.setLastname(principal.getFamilyName());
        user.setEmail(principal.getEmail());
        return user;
    }
}
