package com.charlotte.kies.controller;

import com.charlotte.kies.service.LobsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private LobsterService lobsterService;

    /**** constructor ****/
    public Controller() {
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Greeting() {
        return "hello, world";
    }
}
