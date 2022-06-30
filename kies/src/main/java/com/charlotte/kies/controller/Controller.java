package com.charlotte.kies.controller;

import com.charlotte.kies.model.InflationData;
import com.charlotte.kies.model.LobsterData;
import com.charlotte.kies.model.User;
import com.charlotte.kies.repository.UserRepository;
import com.charlotte.kies.service.InflationService;
import com.charlotte.kies.service.LobsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:3000", allowCredentials = "true")
public class Controller {

    @Autowired
    private LobsterService lobsterService;

    @Autowired
    private InflationService inflationService;

    @Autowired
    private UserRepository userRepository;
    @GetMapping()
    public String Greeting() {
        return "hello, world";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers() { return userRepository.findAll();}

    @RequestMapping(value = "/lobsters/historical-price-data", method = RequestMethod.GET)
    private LobsterData getHistoricalPriceData() {
        LobsterData historicalPriceData;
        historicalPriceData = LobsterService.getHistoricalPriceData();
        return historicalPriceData;
    }

    @RequestMapping(value="/inflation/historical-inflation-rates", method = RequestMethod.GET)
    private InflationData getHistoricalInflationData() {
        InflationData inflationData;
        inflationData = InflationService.getHistoricalInflationData();
        return inflationData;
    }
}
