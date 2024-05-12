package com.NTTT.UserService.Command.Controller;
import com.NTTT.UserService.Command.Model.SendPhoneOtpRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.NTTT.UserService.Command.Data.OtpEntityRepository;
import com.NTTT.UserService.Command.Model.ResponseObject;
import com.NTTT.UserService.Command.Model.changeInfoRequest;
import com.NTTT.UserService.Command.Model.changePasswordRequest;
import com.NTTT.UserService.Command.Service.OtpService;
import com.NTTT.UserService.Command.Service.UserService;
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
    OtpEntityRepository otpEntityRepository;

    @PostMapping("/changeInfo")
    public ResponseObject changeInfo(@RequestBody @Valid changeInfoRequest changeInfoRequest,@RequestHeader(name="Authorization") String token){

            return userService.changeUserInfo(changeInfoRequest,token);
    }
    @PostMapping("/changePassword")
    public ResponseObject changePassword(@RequestBody changePasswordRequest changePasswordRequest) throws IOException {
        return userService.changeUserPassword(changePasswordRequest);
    }

    @PostMapping("/sendPhoneOtp")
    public ResponseObject sendPhoneOtp(@RequestBody SendPhoneOtpRequest sendPhoneOtpRequest) throws IOException {
        return userService.sendPhoneOtp(sendPhoneOtpRequest);
    }


}
