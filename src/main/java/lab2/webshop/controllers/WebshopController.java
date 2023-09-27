package lab2.webshop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lab2.webshop.openapi.model.ShoppingCart;
import lab2.webshop.services.WebshopFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.util.HashMap;
import java.util.Map;

/**
 * Front end controller
 */
@Controller
public class WebshopController {

    WebshopFacade webshopFacade;
    @Autowired
    public WebshopController(WebshopFacade webshopFacade){
        this.webshopFacade = webshopFacade;
    }

    @GetMapping ("/")
    public String home(Model model, HttpSession session, @AuthenticationPrincipal OAuth2User principal, Authentication authentication){
        final ShoppingCart cart = webshopFacade.getShoppingCart(session.getId());
        model.addAttribute("shoppingCart", cart);
        model.addAttribute("sessionId", session.getId());
        return "index";
    }
    @GetMapping("/page/list")
    public String list(@RequestParam(name="name", required = false, defaultValue = "World")String name,
                        Model model,
                        HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final ShoppingCart cart = webshopFacade.getShoppingCart(session.getId());
        model.addAttribute("shoppingCart", cart);
        model.addAttribute("sessionId", session.getId());
        model.addAttribute("products", webshopFacade.getAllProducts());
        return "list";
    }
    @GetMapping("/page/products")
    public String showProduct(@RequestParam(name="productId") String productId,
                              Model model,
                              HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final ShoppingCart cart = webshopFacade.getShoppingCart(session.getId());
        model.addAttribute("sessionId", session.getId());
        model.addAttribute("shoppingCart", cart);
        model.addAttribute("productId", productId);
        model.addAttribute("product", webshopFacade.getOneProduct(productId));
        return "product";
    }
    @PutMapping("/shopping-cart/add-to-cart")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addToCart(@RequestBody Map<String, String> payload, HttpSession session){
        final ShoppingCart cart = webshopFacade.addToCart(payload.get("productId"), session.getId());
        Map<String, Object> response = new HashMap<>();
        response.put("success", !cart.getProductItems().isEmpty());
        response.put("cartItems", cart.getProductItems());
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/shopping-cart/delete-from-cart")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteFromCart(@RequestParam String productId, HttpSession session) {
        final ShoppingCart cart = webshopFacade.deleteFromCart(productId, session.getId());
        Map<String, Object> response = new HashMap<>();
        response.put("cartItems", cart.getProductItems());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/page/login")
    public String login(HttpSession session, Model model){
        final ShoppingCart cart = webshopFacade.getShoppingCart(session.getId());
        model.addAttribute("shoppingCart", cart);
        model.addAttribute("sessionId", session.getId());
        return "login";
    }
    @GetMapping("/users")
    public String finishAuthorize(@AuthenticationPrincipal OAuth2User principal, HttpSession session) {
        return "redirect:/";
    }
    @PostMapping("/logout")
    public void logout(){}

}
