package com.NTTT.UserService.Command.Service;

import com.NTTT.UserService.Command.Data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NTTT.UserService.Command.Data.UserRepository;

@Service
public class OurUserDetailsService {


    private UserRepository userRepository;

    public User loadUserByUsername(String username) {
        return userRepository.findByEmailAddress(username).orElseThrow();
    }


}
