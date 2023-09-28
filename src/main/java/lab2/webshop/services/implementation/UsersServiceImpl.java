package lab2.webshop.services.implementation;

import lab2.webshop.exceptions.NotFoundException;
import lab2.webshop.openapi.model.Provider;
import lab2.webshop.openapi.model.User;
import lab2.webshop.repositories.UsersRepository;
import lab2.webshop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(final UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }
    @Override
    public User addUser(final User newUser) {
        return usersRepository.insert(newUser);
    }

    @Override
    public User getUser(Provider provider, String email) {
        User user = usersRepository.findByProviderAndEmail(provider, email);
        if(user == null) {
            throw new NotFoundException("User with provided credentials was not found");
        }
        return user;
    }
}
