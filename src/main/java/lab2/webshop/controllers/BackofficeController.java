package lab2.webshop.controllers;

import lab2.webshop.util.BackofficeFragments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BackofficeController {

    @GetMapping("/backoffice")
    public String index(@RequestParam(required = false) BackofficeFragments fragment, Model model) {
        if(fragment != null){
            model.addAttribute("templateName", fragment.getTemplateName());
            model.addAttribute("selector", fragment.getSelector());
        }
        return "bo/overview";
    }
}
