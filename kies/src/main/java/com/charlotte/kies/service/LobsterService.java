package com.charlotte.kies.service;

import com.charlotte.kies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class LobsterService {
    @Autowired
    UserRepository userRepository;
//
//    @Autowired
//    private EntityFactoryManager entityFactoryManager;

}
