package com.charlotte.kies.service;

import com.charlotte.kies.model.GoogleUser;
import com.charlotte.kies.model.User;
import com.charlotte.kies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    public AuthService() {
    }

    public User loginGoogleUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).size() == 0) {
                userRepository.save(user);
        }
        return user;
    }
}
