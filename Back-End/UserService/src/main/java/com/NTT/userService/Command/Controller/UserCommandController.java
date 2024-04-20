package com.NTT.userService.Command.Controller;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.NTT.userService.Command.Data.OtpRepository;
import com.NTT.userService.Command.Model.ResponseObject;
import com.NTT.userService.Command.Model.changeInfoRequest;
import com.NTT.userService.Command.Model.changePasswordRequest;
import com.NTT.userService.Command.service.OtpService;
import com.NTT.userService.Command.service.UserService;
import com.NTT.userService.clients.EmailClient;

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
