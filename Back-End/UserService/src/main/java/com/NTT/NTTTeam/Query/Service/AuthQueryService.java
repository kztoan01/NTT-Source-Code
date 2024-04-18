package com.NTT.NTTTeam.Query.Service;

import com.NTT.NTTTeam.Command.Command.CreateUserCommandObject;
import com.NTT.NTTTeam.Command.Controller.AuthCommandController;
import com.NTT.NTTTeam.Command.Model.*;
import com.NTT.NTTTeam.Command.Data.UserRepository;
import com.NTT.NTTTeam.Command.Data.User;
import com.NTT.NTTTeam.Command.service.JWTUtils;
import com.NTT.NTTTeam.Query.Model.ResponseUserDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
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

    @Autowired
    private AuthenticationManager authenticationManager;

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
