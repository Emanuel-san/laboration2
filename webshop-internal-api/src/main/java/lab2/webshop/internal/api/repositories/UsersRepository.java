package lab2.webshop.internal.api.repositories;

import lab2.webshop.openapi.model.Provider;
import lab2.webshop.openapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for users collection.
 */
@Repository
public interface UsersRepository extends MongoRepository<User, String> {
    /**
     * Find a user by service provider and user email
     * @param provider service provider
     * @param email user email
     * @return {@link User}
     */
    User findByProviderAndEmail(Provider provider, String email);
}
