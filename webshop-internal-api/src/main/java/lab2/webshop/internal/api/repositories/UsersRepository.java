package lab2.webshop.internal.api.repositories;

import lab2.webshop.openapi.model.Provider;
import lab2.webshop.openapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<User, String> {

    User findByProviderAndEmail(Provider provider, String email);
}
