package lab2.webshop.web.services.spring;

import lab2.webshop.openapi.model.User;
import lab2.webshop.web.services.WebshopFacade;
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


    /* Intercept request and add users role to authority grant. */
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws UsernameNotFoundException {
        OidcUser defaultOidcUser = super.loadUser(userRequest);
        User user = webshopFacade.isFirstTimeLogin((DefaultOidcUser) defaultOidcUser);
        Collection<GrantedAuthority> authorities = new ArrayList<>(defaultOidcUser.getAuthorities());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getValue()));
        return new DefaultOidcUser(authorities, defaultOidcUser.getIdToken(), defaultOidcUser.getUserInfo());
    }
}
