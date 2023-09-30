package lab2.webshop.services.spring;

import lab2.webshop.openapi.model.User;
import lab2.webshop.services.WebshopFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomOidcUserService extends OidcUserService {

    WebshopFacade webshopFacade;

    @Autowired
    public CustomOidcUserService(WebshopFacade webshopFacade) {
        this.webshopFacade = webshopFacade;
    }
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws UsernameNotFoundException {
        OidcUser defaultOidcUser = super.loadUser(userRequest);
        User user = webshopFacade.addUserOnFirstLogin((DefaultOidcUser) defaultOidcUser);
        Collection<GrantedAuthority> authorities = new ArrayList<>(defaultOidcUser.getAuthorities());
        authorities.add(new SimpleGrantedAuthority("ROLE_CUSTOM"));
        OidcUser customOidcUser = new DefaultOidcUser(authorities, defaultOidcUser.getIdToken(), defaultOidcUser.getUserInfo());
        return defaultOidcUser;
    }
}
