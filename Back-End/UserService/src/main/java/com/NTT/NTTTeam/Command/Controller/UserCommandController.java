package com.NTT.NTTTeam.Command.Controller;
import com.NTT.NTTTeam.clients.EmailClient;
import com.NTT.NTTTeam.Command.Model.ResponseObject;
import com.NTT.NTTTeam.Command.Model.changeInfoRequest;
import com.NTT.NTTTeam.Command.Model.changePasswordRequest;
import com.NTT.NTTTeam.Command.Data.OtpRepository;
import com.NTT.NTTTeam.Command.service.OtpService;
import com.NTT.NTTTeam.Command.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
