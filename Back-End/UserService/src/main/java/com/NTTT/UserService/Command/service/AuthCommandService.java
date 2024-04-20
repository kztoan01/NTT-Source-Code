package com.NTTT.UserService.Command.service;

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

import com.NTTT.UserService.Command.Command.CreateUserCommandObject;
import com.NTTT.UserService.Command.Controller.AuthCommandController;
import com.NTTT.UserService.Command.Data.User;
import com.NTTT.UserService.Command.Data.UserRepository;
import com.NTTT.UserService.Command.Model.*;
import com.NTTT.UserService.Query.Model.ResponseUserDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class AuthCommandService {

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

    public ResponseObject signUp(RegisterRequest registrationRequest){
        ResponseObject responseObject = new ResponseObject();
        try {
            boolean isExisted = false;
            List<ErrorDTO> errors = new ArrayList<>();
                if(userRepository.existUserByPhoneNumber(registrationRequest.getPhoneNumber())) {
                    errors.add(new ErrorDTO("DuplicatedPhonenumber","Phone number is registered!"));
                    isExisted=true;
                }
                if(userRepository.existsUserByUserName(registrationRequest.getUserName())) {
                    errors.add(new ErrorDTO("DuplicatedUsername","User name is taken!"));
                    isExisted=true;
                }
                if(userRepository.existUserByEmailAddress(registrationRequest.getGmail())) {
                    errors.add(new ErrorDTO("DuplicatedGmail","Email address is taken!"));
                    isExisted=true;
                }
                if(registrationRequest.getPhoneNumber().length() != 10)
                {
                    errors.add(new ErrorDTO("PhonenumberFormat","Phone number is invalid!"));
                    isExisted=true;
                }
                if(!patternMatches(registrationRequest.getGmail(), "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
                {
                    errors.add(new ErrorDTO("GmailFormat","Gmail address is invalid!"));
                    isExisted=true;
                }

                if(isExisted)
                {
                    responseObject.setChangeSuccessfully(false);
                    responseObject.setErrors(errors);
                    responseObject.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
                }
                else {
                    CreateUserCommandObject command =
                            new CreateUserCommandObject(UUID.randomUUID().toString(),null,null,registrationRequest.getPhoneNumber(),registrationRequest.getGmail(),registrationRequest.getUserName(), passwordEncoder.encode(registrationRequest.getPassword()), null,null,false,1);
                    commandGateway.sendAndWait(command);
                responseObject.setMessage("Register successfully!");
                responseObject.setChangeSuccessfully(true);
                responseObject.setStatusCode(HttpStatus.CREATED.value());
//                responseObject.setResponeUserDTO(modelMapper.map(ourUserResult, ResponeUserDTO.class));
                }
        } catch (DataAccessException e) {
            responseObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            logger.info("AuthService:" + e.getMessage());
        }
        return responseObject;
    }


    public ResponseObject signIn(AuthenticationRequest signinRequest){
        ResponseObject response = new ResponseObject();

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getGmail(),signinRequest.getPassword()));
            User user = userRepository.findByEmailAddress(signinRequest.getGmail());
            if(user!=null)
            {
                System.out.println("USER IS: "+ user);
                var jwt = jwtUtils.generateToken(user);
                var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshToken);
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully Signed In");
                response.setResponseUserDTO(modelMapper.map(user, ResponseUserDTO.class));
            }
            else
            {
                response.setStatusCode(404);
                response.getErrors().add(new ErrorDTO("LoginFalse","Username or password is incorrect!"));
            }
        }catch (Exception e){
            response.setStatusCode(500);
            response.getErrors().add(new ErrorDTO("Login error",e.getMessage()));
        }
        return response;
    }

    public ResponseObject refreshToken(ResponseObject refreshTokenRequest){
        ResponseObject response = new ResponseObject();
        String ourUsername = jwtUtils.extractUsername(refreshTokenRequest.getToken());
        User users = userRepository.findByUserName(ourUsername);
        if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), users)) {
            users.setUserName(refreshTokenRequest.getMessage());
            var jwt = jwtUtils.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return response;
    }
}
