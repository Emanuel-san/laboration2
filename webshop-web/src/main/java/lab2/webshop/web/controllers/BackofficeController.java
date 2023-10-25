package lab2.webshop.web.controllers;

import lab2.webshop.openapi.model.ProductEntity;
import lab2.webshop.web.services.BackofficeFacade;
import lab2.webshop.web.util.BackofficeFragments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BackofficeController {
    final BackofficeFacade backofficeFacade;

    @Autowired
    public BackofficeController(BackofficeFacade backofficeFacade){
        this.backofficeFacade = backofficeFacade;
    }
    /**
     * Backoffice overview
     */
    @GetMapping("/backoffice")
    public String index(@RequestParam(required = false) BackofficeFragments fragment, Model model) {
        setFragment(model, fragment);
        return "bo/overview";
    }

    /**
     * Backoffice add product overview
     */
    @PostMapping(value = "/backoffice/products/{fragment}")
    public String add(@PathVariable BackofficeFragments fragment,
                      ProductEntity productEntity,
                      Model model) {
        setFragment(model, fragment);
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
    /**
     * Backoffice edit product overview
     */
    @PutMapping(value = "/backoffice/products/{fragment}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> edit(@PathVariable BackofficeFragments fragment,
                                                    @RequestBody ProductEntity productEntity,
                                                    Model model) {
        setFragment(model, fragment);
        ProductEntity updatedProduct = backofficeFacade.updateProduct(productEntity);
        Map<String, Object> response = new HashMap<>();
        if(updatedProduct != null) {
            response.put("updatedProduct", updatedProduct);
        }
        return ResponseEntity.ok(response);
    }
    /**
     * Backoffice delete product overview
     */
    @DeleteMapping(value = "/backoffice/products/{fragment}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> delete(@PathVariable BackofficeFragments fragment,
                                                    @RequestParam String productId,
                                                    Model model) {
        setFragment(model, fragment);
        ProductEntity updatedProduct = backofficeFacade.deleteProduct(productId);
        Map<String, Object> response = new HashMap<>();
        if(updatedProduct != null) {
            response.put("success", true);
        }
        else {
            response.put("success", false);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/backoffice/products/{fragment}")
    public String getProduct(@PathVariable BackofficeFragments fragment, @RequestParam String productId, Model model) {
        setFragment(model, fragment);
        ProductEntity product = backofficeFacade.getProduct(productId);
        if(product != null) {
            model.addAttribute("searchedProduct", product);
        }
        return "bo/overview";
    }
    private void setFragment(Model model, BackofficeFragments fragment) {
        if(fragment != null){
            model.addAttribute("templateName", fragment.getTemplateName());
            model.addAttribute("selector", fragment.getSelector());
        }
    }

}
