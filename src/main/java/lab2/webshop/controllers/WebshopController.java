package lab2.webshop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lab2.webshop.services.ProductService;
import lab2.webshop.services.WebshopFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/page")
public class WebshopController {

    WebshopFacade webshopFacade;
    @Autowired
    public WebshopController(WebshopFacade webshopFacade){
        this.webshopFacade = webshopFacade;
    }
    @GetMapping("/list")
    public String index(@RequestParam(name="name", required = false, defaultValue = "World")String name,
                        Model model,
                        HttpServletRequest request) {
        model.addAttribute("product", webshopFacade.getOneProduct("prd1"));
        return "list";
    }
}
