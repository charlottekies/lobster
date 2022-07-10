package com.charlotte.kies.controller;

import com.charlotte.kies.model.GoogleUser;
import com.charlotte.kies.model.User;
import com.charlotte.kies.repository.UserRepository;
import com.charlotte.kies.security.GoogleIdToken;
import com.charlotte.kies.security.GoogleIdTokenVerifier;
import com.charlotte.kies.service.AuthService;
import com.google.api.Authentication;
import com.google.api.client.auth.openidconnect.IdToken;
import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.auth.openidconnect.IdTokenVerifier;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.apache.catalina.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.core;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.util.Collections;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@CrossOrigin(origins = "http://localhost:3000, http://localhost:8080", allowCredentials = "true")
public class AuthController {

    @Autowired
    private AuthService authservice;

    @Autowired
    private UserRepository userRepository;

    @Value("${CLIENT_ID}")
    private String CLIENT_ID;


    @PreAuthorize("PermitAll")
    @RequestMapping(value = "/google/signin/{token}", method = RequestMethod.POST)
    public User getUsers(@RequestBody GoogleUser googleUser, @PathVariable String token, HttpServletRequest req) throws GeneralSecurityException, IOException {
        User user = new User();
        user.setEmail(googleUser.getEmail());
        user.setName(googleUser.getName());
        user.setFirstName(googleUser.getGiven_name());
        user.setLastName(googleUser.getFamily_name());
        user.setUsername(googleUser.getEmail());
        user.setRole("user");
        authservice.loginGoogleUser(user);

//        // looks like this gets an auth token from teh username and password
//        // can be substituted with google auth token?
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
//
//        // an authentication object is generated from teh auth token
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//
//        // this seems to set the jwt token with which to authenticate a user
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String jwt = tokenProvider.createToken(authentication, false);
//
//        // if we already hae a jwt token, can't we just use that
//
//        // find the user by the username passed in
//        User user = userDao.findByUsername(loginDto.getUsername());
//
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
//        return new ResponseEntity<>(new LoginResponse(jwt, user), httpHeaders, HttpStatus.OK);

//            UsernamePasswordAuthenticationToken authReq
//                    = new UsernamePasswordAuthenticationToken(user, "");
//            Authentication auth = authManager.authenticate(authReq);
//
//            SecurityContext sc = SecurityContextHolder.getContext();
//            sc.setAuthentication(auth);
//            HttpSession session = req.getSession(true);
//            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
        return user;
    }

}
