package lab2.webshop.internal.api.controllers;

import lab2.webshop.internal.api.exceptions.NotFoundException;
import lab2.webshop.internal.api.services.UsersService;
import lab2.webshop.openapi.api.UsersApi;
import lab2.webshop.openapi.model.Provider;
import lab2.webshop.openapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class UsersController implements UsersApi {
    final UsersService usersService;

    @Autowired
    public UsersController(final UsersService usersService){
        this.usersService = usersService;
    }

    @Override
    public ResponseEntity<User> addUser(final User newUser){
        return ResponseEntity.ok(usersService.addUser(newUser));
    }

    @Override
    public ResponseEntity<User> getUser(final Provider provider, String email) {
        User user;
        try {
            user = usersService.getUser(provider, email);
        }
        catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }
}
