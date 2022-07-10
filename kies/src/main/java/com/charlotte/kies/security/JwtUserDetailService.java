
package com.charlotte.kies.security;
import java.util.ArrayList;

import com.charlotte.kies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // todo: if repository has user with that username
        if (userRepository.findByEmail(username).size() == 1) {
            return new User(username,"",new ArrayList<>());
//        if ("".equals(username)) {
//            return new User(username, "",
//                    new ArrayList<>());
        } else {
            // todo: make new user , return it
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}