package com.NTT.userService.Command.service;


import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.NTT.userService.Command.Command.UpdateUserCommandObject;
import com.NTT.userService.Command.Controller.AuthCommandController;
import com.NTT.userService.Command.Data.OtpRepository;
import com.NTT.userService.Command.Data.User;
import com.NTT.userService.Command.Data.UserRepository;
import com.NTT.userService.Command.Model.ErrorDTO;
import com.NTT.userService.Command.Model.ResponseObject;
import com.NTT.userService.Command.Model.changeInfoRequest;
import com.NTT.userService.Command.Model.changePasswordRequest;
import com.NTT.userService.Query.Service.AuthQueryService;
import com.NTT.userService.clients.EmailClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class UserService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthCommandService authCommandService;

    @Autowired
    AuthQueryService authQueryService;

    @Autowired
    OtpRepository otpRepository;

    @Autowired
    OtpService otpService;

    @Autowired
    CommandGateway commandGateway;

    Logger logger
            = LoggerFactory.getLogger(AuthCommandController.class);


    private EmailClient emailClient;

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }


    public ResponseObject changeUserPassword(changePasswordRequest changePasswordRequest) {
        ResponseObject responseObject = new ResponseObject();
        try {
            User foundUser = userRepository.findByUserId(changePasswordRequest.getUserId());
             if(foundUser != null && otpRepository.findByUserId(foundUser.getId()) == null && changePasswordRequest.getOtp()==0)
            {
                Random rand = new Random();
                int otp = 100000 + rand.nextInt(900000);
                otpService.AddOtp(otp,"ChangePassword", foundUser.getId());
                emailClient.sendMail("Changing password otp","Hi,We would like to send you OTP:"+otp,foundUser.getEmailAddress());
                responseObject.setMessage("Send email successfully!");
                responseObject.setChangeSuccessfully(false);
            }
            else if (otpRepository.findByUserId(foundUser.getId()) != null && changePasswordRequest.getOtp() != 0)
            {
                    if(changePasswordRequest.getOtp() == otpRepository.findByUserId(foundUser.getId()).getOtp())
                    {
                        foundUser.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
                        userRepository.save(foundUser);
                        otpRepository.delete(otpRepository.findByUserId(foundUser.getId()));
                        responseObject.setMessage("Change Password successfully!");
                        responseObject.setChangeSuccessfully(true);
                    }
                    else
                    {
                        responseObject.setMessage("Invalid otp!");
                        responseObject.setChangeSuccessfully(false);
                    }
            }
            else {
                responseObject.setMessage("Change Password failed.User not found!");
                responseObject.setChangeSuccessfully(false);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while changing the password: " + e.getMessage());
        }
        return responseObject;
    }

    public ResponseObject changeUserInfo(changeInfoRequest changeInfoRequest,String token) {
        ResponseObject responseObject = new ResponseObject();
        try {
            User ourUser = userRepository.findByUserId(changeInfoRequest.getUserID());
            boolean isExisted = false;
            List<ErrorDTO> errors = new ArrayList<>();
            if(userRepository.existUserByPhoneNumber(changeInfoRequest.getPhoneNumber()) && !Objects.equals(changeInfoRequest.getPhoneNumber(), ourUser.getPhoneNumber())) {
                errors.add(new ErrorDTO("DuplicatedPhonenumber","Phone number is registered!"));
                isExisted=true;
            }
            if(userRepository.existsUserByUserName(changeInfoRequest.getUsername()) && !Objects.equals(changeInfoRequest.getUsername(), ourUser.getUserName())) {
                errors.add(new ErrorDTO("DuplicatedUsername","User name is taken!"));
                isExisted=true;
            }
            if(userRepository.existUserByEmailAddress(changeInfoRequest.getEmailAddress()) && !Objects.equals(changeInfoRequest.getEmailAddress(), ourUser.getEmailAddress())) {
                errors.add(new ErrorDTO("DuplicatedGmail","Email address is taken!"));
                isExisted=true;
            }
            if(changeInfoRequest.getPhoneNumber().length() != 10)
            {
                errors.add(new ErrorDTO("PhonenumberFormat","Phone number is invalid!"));
                isExisted=true;
            }
            if(!patternMatches(changeInfoRequest.getEmailAddress(), "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
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
            else
            {
                User foundUser = userRepository.findByUserId(changeInfoRequest.getUserID());
                if (foundUser != null) {
                    ResponseObject responseObject1 = new ResponseObject();
                    responseObject1.setToken(token.substring(7));
                    responseObject1.setMessage(changeInfoRequest.getUsername());
                    String newToken = authCommandService.refreshToken(responseObject1).getToken();
                    responseObject.setToken(newToken);
                    UpdateUserCommandObject updateUserCommandObject = new UpdateUserCommandObject(changeInfoRequest.getUserID(),changeInfoRequest.getFirstName(),changeInfoRequest.getLastName(),changeInfoRequest.getPhoneNumber(),changeInfoRequest.getEmailAddress(),changeInfoRequest.getUsername(),changeInfoRequest.getFacebook(),changeInfoRequest.getApple());
                    commandGateway.sendAndWait(updateUserCommandObject);
                    responseObject.setMessage("Change user information successfully!");
                    responseObject.setChangeSuccessfully(true);
                } else {
                    responseObject.setMessage("Change user information failed.User not found!");
                    responseObject.setChangeSuccessfully(false);
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while changing user information: " + e.getMessage());
        }
        return responseObject;
    }


}
