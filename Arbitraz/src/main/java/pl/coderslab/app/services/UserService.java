package pl.coderslab.app.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.model.User;
import pl.coderslab.app.repositories.UserRepository;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

}
