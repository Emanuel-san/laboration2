package lab2.webshop.controllers;

import lab2.webshop.openapi.model.ProductEntity;
import lab2.webshop.services.BackofficeFacade;
import lab2.webshop.util.BackofficeFragments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.standard.expression.Fragment;

import java.util.Map;

@Controller
public class BackofficeController {
    BackofficeFacade backofficeFacade;
    @Autowired
    public BackofficeController(BackofficeFacade backofficeFacade){
        this.backofficeFacade = backofficeFacade;
    }
    @GetMapping("/backoffice")
    public String index(@RequestParam(required = false) BackofficeFragments fragment, Model model) {
        setTemplateFragment(model, fragment);
        return "bo/overview";
    }

    @RequestMapping(value = "/backoffice/products", method = {RequestMethod.POST, RequestMethod.PUT})
    public String add(@RequestParam BackofficeFragments fragment,
                      ProductEntity productEntity,
                      Model model) {
        setTemplateFragment(model, fragment);
        ProductEntity product = backofficeFacade.addProduct(productEntity);
        if(product != null){
            model.addAttribute("success", true);
            model.addAttribute("addedProduct", product);
        }
        else {
            model.addAttribute("success", false);
        }
        return "bo/overview";
    }

    private void setTemplateFragment(Model model, BackofficeFragments fragment) {
        if(fragment != null){
            model.addAttribute("templateName", fragment.getTemplateName());
            model.addAttribute("selector", fragment.getSelector());
        }
    }
}
