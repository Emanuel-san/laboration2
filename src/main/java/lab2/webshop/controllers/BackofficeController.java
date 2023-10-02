package lab2.webshop.controllers;

import lab2.webshop.openapi.model.ProductEntity;
import lab2.webshop.util.BackofficeFragments;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @RequestMapping(value = "/backoffice/products", method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public ResponseEntity<Map<String, Object>> add(@RequestBody Map<String, String> payload) {
        return null;
    }
}
