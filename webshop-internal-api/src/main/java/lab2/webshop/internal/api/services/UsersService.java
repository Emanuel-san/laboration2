package lab2.webshop.internal.api.services;

import lab2.webshop.openapi.model.Provider;
import lab2.webshop.openapi.model.User;
/**
 * Service interface for users
 */
public interface UsersService {
    /**
     * Add a new user to the database
     * @param newUser user info {@link User}
     * @return added user
     */
    User addUser(User newUser);

    /**
     * Fetch a user by service provider and user email
     * @param provider service provider
     * @param email user email
     * @return {@link User}
     */
    User getUser(Provider provider, String email);
}
