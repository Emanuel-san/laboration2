package lab2.webshop.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
import java.util.UUID;

/**
 * Webshop front end controller
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
    public String home(@CookieValue(value="cartId", defaultValue = "none") String cartId,
                       final Model model,
                       @AuthenticationPrincipal OAuth2User principal,
                       HttpServletResponse response) {
        addCommonAttributes(model, response, principal, cartId);
        return "shop/index";
    }
    @GetMapping("/page/list")
    public String list(@RequestParam(name="search", required = false)String name,
                       @CookieValue(value="cartId", defaultValue = "none") String cartId,
                       HttpServletResponse response,
                       Model model,
                       @AuthenticationPrincipal OAuth2User principal) {
        addCommonAttributes(model, response, principal, cartId);
        model.addAttribute("products", webshopFacade.getAllProducts());
        return "shop/list";
    }
    @GetMapping("/page/products")
    public String showProduct(@RequestParam(name=PRODUCT_IDENTIFIER_ATTRIBUTE) String productId,
                              @CookieValue(value="cartId", defaultValue = "none") String cartId,
                              HttpServletResponse response,
                              Model model,
                              @AuthenticationPrincipal OAuth2User principal) {
        addCommonAttributes(model, response, principal, cartId);
        model.addAttribute(PRODUCT_IDENTIFIER_ATTRIBUTE, productId);
        model.addAttribute("product", webshopFacade.getOneProduct(productId));
        return "shop/product";
    }
    @GetMapping("/page/login")
    public String login(Model model,
                        @AuthenticationPrincipal OAuth2User principal,
                        @CookieValue(value="cartId", defaultValue = "none") String cartId,
                        HttpServletResponse response){
        addCommonAttributes(model, response, principal, cartId);
        return "shop/login";
    }
    @PutMapping("/shopping-cart/add-to-cart")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addToCart(@RequestBody Map<String, String> payload,
                                                         @CookieValue(value="cartId", defaultValue = "none") String cartId){
        final ShoppingCart cart = webshopFacade.addToCart(payload.get(PRODUCT_IDENTIFIER_ATTRIBUTE), cartId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", !cart.getProductItems().isEmpty());
        response.put(CART_ITEMS_LIST_ATTRIBUTE, cart.getProductItems());
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/shopping-cart/delete-from-cart")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteFromCart(@RequestParam String productId,
                                                              @CookieValue(value="cartId", defaultValue = "none") String cartId) {
        final ShoppingCart cart = webshopFacade.deleteFromCart(productId, cartId);
        Map<String, Object> response = new HashMap<>();
        response.put(CART_ITEMS_LIST_ATTRIBUTE, cart.getProductItems());
        return ResponseEntity.ok(response);
    }
    private void addCommonAttributes(Model model, HttpServletResponse response, OAuth2User principal, String cartId) {
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
        final ShoppingCart cart = webshopFacade.getShoppingCart(hasCart(cartId, response));
        model.addAttribute(CART_OBJECT_ATTRIBUTE, cart);
    }

    private String hasCart(String cartId, HttpServletResponse response) {
        if(cartId.equals("none")) {
            cartId = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("cartId", cartId);
            response.addCookie(cookie);
        }
        return cartId;
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
