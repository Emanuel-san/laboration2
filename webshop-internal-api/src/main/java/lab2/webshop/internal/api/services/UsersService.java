package lab2.webshop.internal.api.services;

import lab2.webshop.openapi.model.Provider;
import lab2.webshop.openapi.model.User;

public interface UsersService {

    User addUser(User newUser);
    User getUser(Provider provider, String email);
}
