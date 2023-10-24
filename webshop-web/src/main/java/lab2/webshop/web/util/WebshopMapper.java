package lab2.webshop.web.util;

import lab2.webshop.openapi.model.*;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.ArrayList;

final public class WebshopMapper {

    public static User mapUserFromOidc(final DefaultOidcUser oidcUser) {
        final User user = new User();
        user.setProvider(getProvider(oidcUser));
        user.setRole(Role.USER);
        user.setFirstname(oidcUser.getGivenName());
        user.setLastname(oidcUser.getFamilyName());
        user.setEmail(oidcUser.getEmail());
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

    public static Provider getProvider(final DefaultOidcUser oidcUser) {
        if(oidcUser.getIssuer().getAuthority().equals(Authorities.GOOGLE.getName())) {
            return Provider.GOOGLE;
        }
        return Provider.LOCAL;
    }
}
