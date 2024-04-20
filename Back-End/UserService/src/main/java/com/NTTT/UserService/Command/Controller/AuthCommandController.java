package com.NTTT.UserService.Command.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.NTTT.UserService.Command.Model.AuthenticationRequest;
import com.NTTT.UserService.Command.Model.RegisterRequest;
import com.NTTT.UserService.Command.Model.ResponseObject;
import com.NTTT.UserService.Command.service.AuthCommandService;
import com.NTTT.UserService.Query.Service.AuthQueryService;



@RestController
@RequestMapping("/auth")
public class AuthCommandController {

    @Autowired
    private AuthCommandService authCommandService;

    @Autowired
    private AuthQueryService authQueryService;

    Logger logger
            = LoggerFactory.getLogger(AuthCommandController.class);

    @PostMapping("/signup")
    public ResponseObject signUp(@RequestBody RegisterRequest signUpRequest){
        ResponseObject responseObject = new ResponseObject();
        try {
           responseObject =  authCommandService.signUp(signUpRequest);
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
    @PostMapping("/refresh")
    public ResponseObject refreshToken(@RequestBody ResponseObject refreshTokenRequest){
        return authCommandService.refreshToken(refreshTokenRequest);
    }

}
