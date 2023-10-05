package lab2.webshop.controllers;

import lab2.webshop.openapi.model.ProductEntity;
import lab2.webshop.services.BackofficeFacade;
import lab2.webshop.util.BackofficeFragments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/backoffice/products/{fragment}")
    public String add(@PathVariable BackofficeFragments fragment,
                      ProductEntity productEntity,
                      Model model) {
        setTemplateFragment(model, fragment);
        ProductEntity product = backofficeFacade.addProduct(productEntity);
        if(product != null) {
            model.addAttribute("success", true);
            model.addAttribute("addedProduct", product);
        }
        else {
            model.addAttribute("success", false);
        }
        return "bo/overview";
    }
    @PutMapping(value = "/backoffice/products/{fragment}")
    public String edit(@PathVariable BackofficeFragments fragment,
                      ProductEntity productEntity,
                      Model model) {
        setTemplateFragment(model, fragment);
        return "bo/overview";
    }

    @GetMapping(value = "/backoffice/products/{fragment}")
    public String getProduct(@PathVariable BackofficeFragments fragment, @RequestParam String productId, Model model) {
        setTemplateFragment(model, fragment);
        ProductEntity product = backofficeFacade.getProduct(productId);
        if(product != null) {
            model.addAttribute("searchedProduct", product);
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
