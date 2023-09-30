package lab2.webshop.util;

import lab2.webshop.openapi.model.Provider;
import lab2.webshop.openapi.model.ShoppingCart;
import lab2.webshop.openapi.model.ShoppingCartEntity;
import lab2.webshop.openapi.model.User;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.ArrayList;

final public class WebshopModelMapper {

    public static User mapUserFromOidc(final DefaultOidcUser principal) {
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

    public static ShoppingCart mapFromCartEntity(final ShoppingCartEntity entity){
        final ShoppingCart cart = new ShoppingCart();
        if(entity == null) {
            // Something went wrong if entity is null, so we just initiate a new empty list.
            cart.setProductItems(new ArrayList<>());
            return cart;
        }
        cart.setProductItems(entity.getProductItems());
        return cart;
    }
}
