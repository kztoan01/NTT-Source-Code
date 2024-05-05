package com.NTTT.UserService.Query.Service;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.NTTT.UserService.Command.Controller.AuthCommandController;
import com.NTTT.UserService.Command.Data.UserRepository;
import com.NTTT.UserService.Command.Service.JWTUtils;

import java.util.regex.Pattern;

@Service
public class AuthQueryService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CommandGateway commandGateway;


    Logger logger
            = LoggerFactory.getLogger(AuthCommandController.class);

    @Autowired
    private ModelMapper modelMapper;
    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }


}
