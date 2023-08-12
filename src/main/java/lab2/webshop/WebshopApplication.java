package lab2.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class WebshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebshopApplication.class, args);
    }
}
