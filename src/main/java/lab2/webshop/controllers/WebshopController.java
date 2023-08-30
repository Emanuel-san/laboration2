package lab2.webshop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebshopController {

    final private static String PRODUCTS_URI = "/products";

    @GetMapping("/page/list")
    public String index(@RequestParam(name="name", required = false, defaultValue = "World")String name, Model model, HttpServletRequest request) {
        model.addAttribute("name", name);
        return "list";
    }
}
