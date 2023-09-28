package lab2.webshop.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                //.authorizeHttpRequests((requests) -> requests.requestMatchers("/shopping-cart/add-to-cart**", "/shopping-cart/delete-from-cart**").permitAll())
                .oauth2Login(oauth2 -> oauth2.loginPage("/page/login").defaultSuccessUrl("/"));
        return http.build();
    }
}
