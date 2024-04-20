package com.NTTT.UserService.Command.Controller;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.NTTT.UserService.Command.Data.OtpRepository;
import com.NTTT.UserService.Command.Model.ResponseObject;
import com.NTTT.UserService.Command.Model.changeInfoRequest;
import com.NTTT.UserService.Command.Model.changePasswordRequest;
import com.NTTT.UserService.Command.service.OtpService;
import com.NTTT.UserService.Command.service.UserService;
import com.NTTT.UserService.clients.EmailClient;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserCommandController {

    @Autowired
    UserService userService;


    private EmailClient emailClient;

    @Autowired
    OtpService otpService;

    @Autowired
    OtpRepository otpRepository;

    @PostMapping("/changeInfo")
    public ResponseObject changeInfo(@RequestBody @Valid changeInfoRequest changeInfoRequest,@RequestHeader(name="Authorization") String token){

            return userService.changeUserInfo(changeInfoRequest,token);
    }
    @PostMapping("/changePassword")
    public ResponseObject changePassword(@RequestBody changePasswordRequest changePasswordRequest) throws IOException {
        return userService.changeUserPassword(changePasswordRequest);
    }
}
