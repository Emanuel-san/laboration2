package lab2.webshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BackofficeController {

    @GetMapping("/backoffice")
    public String index(){
        return "bo/overview";
    }
}
