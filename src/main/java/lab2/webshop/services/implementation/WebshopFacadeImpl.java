package lab2.webshop.services.implementation;

import lab2.webshop.controllers.OrderController;
import lab2.webshop.controllers.ProductController;
import lab2.webshop.controllers.ShoppingCartController;
import lab2.webshop.controllers.UsersController;
import lab2.webshop.openapi.model.*;
import lab2.webshop.services.WebshopFacade;
import lab2.webshop.util.WebshopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebshopFacadeImpl implements WebshopFacade {
    private final ProductController productController;
    private final ShoppingCartController shoppingCartController;
    private final OrderController orderController;
    private final UsersController usersController;

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
    public ShoppingCart getShoppingCart(final String cartId) {
        final ResponseEntity<ShoppingCartEntity> response = shoppingCartController.getShoppingCart(cartId);
        final ShoppingCartEntity shoppingCartEntity = response.getBody();
        return WebshopMapper.mapFromCartEntity(shoppingCartEntity);
    }

    @Override
    public ShoppingCart addToCart(final String productId, final String cartId) {
        final ProductEntity productEntity = productController.getProduct(productId).getBody();
        final ShoppingCartEntity shoppingCartEntity = shoppingCartController.addToShoppingCart(cartId, productEntity).getBody();
        return WebshopMapper.mapFromCartEntity(shoppingCartEntity);
    }

    @Override
    public ShoppingCart deleteFromCart(final String productId, final String cartId) {
        final ShoppingCartEntity shoppingCartEntity = shoppingCartController.deleteFromShoppingCart(productId,cartId).getBody();
        return WebshopMapper.mapFromCartEntity(shoppingCartEntity);
    }
    @Override
    public User getUser(OAuth2User principal) {
        if(principal instanceof DefaultOidcUser user){
            ResponseEntity<User> response = usersController.getUser(WebshopMapper.getProvider(user), user.getEmail());
            return response.getBody();
        }
        return null;
    }
    @Override
    public User isFirstTimeLogin(DefaultOidcUser user) {
        User shopUser = WebshopMapper.mapUserFromOidc(user);
        ResponseEntity<User> response = usersController.getUser(shopUser.getProvider(), shopUser.getEmail());
        return response
                .getStatusCode()
                .is2xxSuccessful() ? response.getBody() : usersController.addUser(shopUser).getBody();
    }
}
