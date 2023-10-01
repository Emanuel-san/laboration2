package lab2.webshop.controllers;

import jakarta.servlet.http.HttpSession;
import lab2.webshop.openapi.model.ShoppingCart;
import lab2.webshop.openapi.model.User;
import lab2.webshop.services.WebshopFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Front end controller
 */
@Controller
public class WebshopController {
    private static final String USER_AUTHENTICATED = "authenticated";
    private static final String USER_OBJECT_ATTRIBUTE = "user";
    private static final String CART_OBJECT_ATTRIBUTE = "shoppingCart";
    private static final String CART_ITEMS_LIST_ATTRIBUTE = "cartItems";
    private static final String PRODUCT_IDENTIFIER_ATTRIBUTE = "productId";
    WebshopFacade webshopFacade;
    @Autowired
    public WebshopController(WebshopFacade webshopFacade){
        this.webshopFacade = webshopFacade;
    }

    @GetMapping ("/")
    public String home(final Model model, final HttpSession session, @AuthenticationPrincipal OAuth2User principal){
        addCommonAttributes(model, session, principal);
        return "shop/index";
    }
    @GetMapping("/page/list")
    public String list(@RequestParam(name="search", required = false)String name,
                        Model model,
                        HttpSession session,
                        @AuthenticationPrincipal OAuth2User principal) {
        addCommonAttributes(model, session, principal);
        model.addAttribute("products", webshopFacade.getAllProducts());
        return "shop/list";
    }
    @GetMapping("/page/products")
    public String showProduct(@RequestParam(name=PRODUCT_IDENTIFIER_ATTRIBUTE) String productId,
                              Model model,
                              HttpSession session,
                              @AuthenticationPrincipal OAuth2User principal) {
        addCommonAttributes(model, session, principal);
        model.addAttribute(PRODUCT_IDENTIFIER_ATTRIBUTE, productId);
        model.addAttribute("product", webshopFacade.getOneProduct(productId));
        return "shop/product";
    }
    @GetMapping("/page/login")
    public String login(HttpSession session, Model model, @AuthenticationPrincipal OAuth2User principal){
        addCommonAttributes(model, session, principal);
        return "shop/login";
    }
    @PutMapping("/shopping-cart/add-to-cart")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addToCart(@RequestBody Map<String, String> payload, HttpSession session){
        final ShoppingCart cart = webshopFacade.addToCart(payload.get(PRODUCT_IDENTIFIER_ATTRIBUTE), session.getId());
        Map<String, Object> response = new HashMap<>();
        response.put("success", !cart.getProductItems().isEmpty());
        response.put(CART_ITEMS_LIST_ATTRIBUTE, cart.getProductItems());
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/shopping-cart/delete-from-cart")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteFromCart(@RequestParam String productId, HttpSession session) {
        final ShoppingCart cart = webshopFacade.deleteFromCart(productId, session.getId());
        Map<String, Object> response = new HashMap<>();
        response.put(CART_ITEMS_LIST_ATTRIBUTE, cart.getProductItems());
        return ResponseEntity.ok(response);
    }
    private void addCommonAttributes(Model model, HttpSession session, OAuth2User principal) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean authenticated = false;
        User user = null;
        if(principal instanceof DefaultOidcUser oidcUser) {
            user = webshopFacade.getUser(oidcUser);
        }
        if(user != null) {
            authenticated = true;
            model.addAttribute(USER_OBJECT_ATTRIBUTE, user);
            model.addAttribute("isAdmin", isAdmin(authentication));
        }
        model.addAttribute(USER_AUTHENTICATED, authenticated);
        final ShoppingCart cart = webshopFacade.getShoppingCart(session.getId());
        model.addAttribute(CART_OBJECT_ATTRIBUTE, cart);
        model.addAttribute("sessionId", session.getId());
    }

    private boolean isAdmin(final Authentication authentication){
        if(authentication != null && authentication.isAuthenticated()){
            return authentication.getAuthorities()
                    .stream()
                    .anyMatch(grantedAuthority ->
                            grantedAuthority.getAuthority().equals("ROLE_SHOP_ADMIN")
                    );
        }
        return false;
    }
}
