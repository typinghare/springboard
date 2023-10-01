package us.jameschan.springboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.jameschan.springboard.model.User;
import us.jameschan.springboard.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a user
     * @param id the
     * @return user
     */
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    public User signIn(String username, String password) {
        return null;
    }
}
