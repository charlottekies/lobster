package com.charlotte.kies.controller;

import com.charlotte.kies.repository.UserRepository;
import com.charlotte.kies.service.LobsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private LobsterService lobsterService;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Greeting() {
        return "hello, world";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers() { return userRepository.findAll();}
}
