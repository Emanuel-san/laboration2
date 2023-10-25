package lab2.webshop.web.services.implementation;

import lab2.webshop.openapi.api.OrdersApi;
import lab2.webshop.openapi.api.ProductsApi;
import lab2.webshop.openapi.api.ShoppingCartsApi;
import lab2.webshop.openapi.api.UsersApi;
import lab2.webshop.openapi.model.ProductEntity;
import lab2.webshop.openapi.model.ShoppingCart;
import lab2.webshop.openapi.model.ShoppingCartEntity;
import lab2.webshop.openapi.model.User;
import lab2.webshop.web.services.WebshopFacade;
import lab2.webshop.web.util.WebshopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebshopFacadeImpl implements WebshopFacade {
    private final ProductsApi productsApi;
    private final ShoppingCartsApi shoppingCartsApi;
    private final OrdersApi ordersApi;
    private final UsersApi usersApi;

    @Autowired
    public WebshopFacadeImpl(final ProductsApi productsApi,
                             final ShoppingCartsApi shoppingCartsApi,
                             final OrdersApi ordersApi,
                             final UsersApi usersApi){
        this.productsApi = productsApi;
        this.shoppingCartsApi = shoppingCartsApi;
        this.ordersApi = ordersApi;
        this.usersApi = usersApi;
    }
    @Override
    public List<ProductEntity> getAllProducts() {
        return productsApi.getProducts(null);
    }
    @Override
    public ProductEntity getOneProduct(final String productId){
        return productsApi.getProduct(productId);
    }

    @Override
    public ShoppingCart getShoppingCart(final String cartId) {
        final ShoppingCartEntity shoppingCartEntity = shoppingCartsApi.getShoppingCart(cartId);
        return WebshopMapper.mapFromCartEntity(shoppingCartEntity);
    }

    @Override
    public ShoppingCart addToCart(final String productId, final String cartId) {
        final ProductEntity productEntity = productsApi.getProduct(productId);
        final ShoppingCartEntity shoppingCartEntity = shoppingCartsApi.addToShoppingCart(cartId, productEntity);
        return WebshopMapper.mapFromCartEntity(shoppingCartEntity);
    }

    @Override
    public ShoppingCart deleteFromCart(final String productId, final String cartId) {
        final ShoppingCartEntity shoppingCartEntity = shoppingCartsApi.deleteFromShoppingCart(productId,cartId);
        return WebshopMapper.mapFromCartEntity(shoppingCartEntity);
    }
    @Override
    public User getUser(OAuth2User principal) {
        if(principal instanceof DefaultOidcUser user){
            return usersApi.getUser(WebshopMapper.getProvider(user), user.getEmail());
        }
        //TODO should throw exception instead
        return null;
    }
    @Override
    public User isFirstTimeLogin(DefaultOidcUser user) {
        User shopUser = WebshopMapper.mapUserFromOidc(user);
        ResponseEntity<User> response = usersApi.getUserWithHttpInfo(shopUser.getProvider(), shopUser.getEmail());
        return response
                .getStatusCode()
                .is2xxSuccessful() ? response.getBody() : usersApi.addUser(shopUser);
    }
}
