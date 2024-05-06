package com.NTTT.UserService.Command.Controller;

import com.NTTT.UserService.Command.Model.changePasswordRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import com.NTTT.UserService.Command.Model.AuthenticationRequest;
import com.NTTT.UserService.Command.Model.RegisterRequest;
import com.NTTT.UserService.Command.Model.ResponseObject;
import com.NTTT.UserService.Command.Service.AuthCommandService;
import com.NTTT.UserService.Query.Service.AuthQueryService;



@RestController
@RequestMapping("/auth")
@EnableBinding(Source.class)
public class AuthCommandController {

    @Autowired
    private AuthCommandService authCommandService;

    @Autowired
    private AuthQueryService authQueryService;



    @Autowired
    MessageChannel output;

    Logger logger
            = LoggerFactory.getLogger(AuthCommandController.class);

    @PostMapping("/signup")
    public ResponseObject signUpWithEmail(@RequestBody RegisterRequest signUpRequest){
        ResponseObject responseObject = new ResponseObject();
        try {
           responseObject =  authCommandService.signUpWithEmail(signUpRequest);
        }
        catch(Exception e)
        {
            logger.info("AuthController"+e.getMessage());
        }
        return responseObject;
    }
    @PostMapping("/signin")
    public ResponseObject signIn(@RequestBody AuthenticationRequest request){
        return authCommandService.signIn(request);
    }

    @PostMapping("/ChangeFirstPassword")
    public ResponseObject signIn(@RequestBody changePasswordRequest changePasswordRequest){
        return authCommandService.changeUserPasswordFirstTime(changePasswordRequest);
    }
    @PostMapping("/refresh")
    public ResponseObject refreshToken(@RequestBody ResponseObject refreshTokenRequest){
        return authCommandService.refreshToken(refreshTokenRequest);
    }


}
