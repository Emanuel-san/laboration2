package lab2.webshop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public String home(HttpServletRequest request){
        request.getSession();
        return "index";
    }
    @GetMapping("/page/list")
    public String list(@RequestParam(name="name", required = false, defaultValue = "World")String name,
                        Model model,
                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(session);
        model.addAttribute("product", webshopFacade.getOneProduct("prd1"));
        return "list";
    }
}
