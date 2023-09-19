package lab2.webshop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lab2.webshop.openapi.model.ProductEntity;
import lab2.webshop.openapi.model.ShoppingCart;
import lab2.webshop.services.WebshopFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    WebshopFacade webshopFacade;
    @Autowired
    public WebshopController(WebshopFacade webshopFacade){
        this.webshopFacade = webshopFacade;
    }

    @GetMapping ("/")
    public String home(HttpServletRequest request, Model model){
        final HttpSession session = request.getSession();
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
    @PostMapping("/shopping-cart/addToCart")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addToCart(@RequestBody Map<String, String> payload, HttpSession session){
        ShoppingCart cart = webshopFacade.addToCart(payload.get("productId"), session.getId());
        Map<String, Object> response = new HashMap<>();
        response.put("success", !cart.getProductItems().isEmpty());
        response.put("cartItems", cart.getProductItems());
        return ResponseEntity.ok(response);
    }
}
