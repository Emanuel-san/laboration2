package lab2.webshop.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"lab2.webshop.web", "lab2.webshop.openapi"})
public class WebshopWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebshopWebApplication.class, args);
    }

}
