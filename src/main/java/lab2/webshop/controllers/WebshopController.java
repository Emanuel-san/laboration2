package lab2.webshop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lab2.webshop.openapi.model.ProductEntity;
import lab2.webshop.openapi.model.ShoppingCart;
import lab2.webshop.services.WebshopFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String showProduct(@RequestParam(name="productId", required = true) String productId,
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
}
