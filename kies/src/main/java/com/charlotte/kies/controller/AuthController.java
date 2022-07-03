package com.charlotte.kies.controller;

import com.charlotte.kies.model.GoogleUser;
import com.charlotte.kies.model.User;
import com.charlotte.kies.repository.UserRepository;
import com.charlotte.kies.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:3000", allowCredentials = "true")
public class AuthController {

    @Autowired
    private AuthService authservice;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/google/signin", method = RequestMethod.POST)
    public User getUsers(@RequestBody GoogleUser googleUser) {
        User user = new User();
        user.setEmail(googleUser.getEmail());
        user.setName(googleUser.getName());
        user.setFirstName(googleUser.getGiven_name());
        user.setLastName(googleUser.getFamily_name());
        user.setUsername(googleUser.getEmail());
        authservice.loginGoogleUser(user);

        return user;
        }

}
