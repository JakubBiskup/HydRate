package com.example.hydrate.service;

import com.example.hydrate.model.MyUserDetails;
import com.example.hydrate.model.User;
import com.example.hydrate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> optionalUser=userRepository.findByUsername(username);
        if(optionalUser.isPresent()){
            return new MyUserDetails(optionalUser.get());
        }else {
            throw new UsernameNotFoundException("username: " + username + " was not found");
        }

    }


}
