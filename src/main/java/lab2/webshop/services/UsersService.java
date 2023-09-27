package lab2.webshop.services;

import lab2.webshop.openapi.model.User;
import org.springframework.stereotype.Service;

public interface UsersService {

    User addUser(User newUser);
}
