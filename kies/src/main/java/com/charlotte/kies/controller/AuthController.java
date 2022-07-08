package com.charlotte.kies.controller;

import com.charlotte.kies.model.GoogleUser;
import com.charlotte.kies.model.User;
import com.charlotte.kies.repository.UserRepository;
import com.charlotte.kies.security.GoogleIdToken;
import com.charlotte.kies.security.GoogleIdTokenVerifier;
import com.charlotte.kies.service.AuthService;
import com.google.api.client.auth.openidconnect.IdToken;
import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.auth.openidconnect.IdTokenVerifier;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:3000", allowCredentials = "true")
public class AuthController {

    @Autowired
    private AuthService authservice;

    @Autowired
    private UserRepository userRepository;

    @Value("${CLIENT_ID}")
    private String CLIENT_ID;

    @PreAuthorize("isAuthenticated")
    @RequestMapping(value = "/google/signin/{token}", method = RequestMethod.POST)
    public User getUsers(@RequestBody GoogleUser googleUser, @PathVariable String token) throws GeneralSecurityException, IOException {
        User user = new User();
        user.setEmail(googleUser.getEmail());
        user.setName(googleUser.getName());
        user.setFirstName(googleUser.getGiven_name());
        user.setLastName(googleUser.getFamily_name());
        user.setUsername(googleUser.getEmail());
        user.setRole("user");
        authservice.loginGoogleUser(user);



        return user;
    }

}
