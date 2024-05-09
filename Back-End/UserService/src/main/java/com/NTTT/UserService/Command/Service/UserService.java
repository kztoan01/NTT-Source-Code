package com.NTTT.UserService.Command.Service;


import com.NTTT.UserService.Command.Model.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.NTTT.UserService.Command.Command.UpdateUserCommandObject;
import com.NTTT.UserService.Command.Controller.AuthCommandController;
import com.NTTT.UserService.Command.Data.OtpEntityRepository;
import com.NTTT.UserService.Command.Data.User;
import com.NTTT.UserService.Command.Data.UserRepository;
import com.NTTT.UserService.Query.Service.AuthQueryService;
import com.NTTT.UserService.clients.EmailClient;

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
    OtpEntityRepository otpEntityRepository;

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    OtpService otpService;

    @Autowired
    CommandGateway commandGateway;

    Logger logger
            = LoggerFactory.getLogger(AuthCommandController.class);

    Random rand = new Random();


    @Autowired
    private EmailClient emailClient;

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    /**
     * This method is used to change the password of user.
     */
    public ResponseObject changeUserPassword(changePasswordRequest changePasswordRequest) {
        ResponseObject responseObject = new ResponseObject();
        try {
            User foundUser = userRepository.findByUserId(changePasswordRequest.getUserId()).orElseThrow();
             if(foundUser != null && otpEntityRepository.findByUserEmail(foundUser.getEmailAddress()) == null && changePasswordRequest.getOtp()==0)
            {
                logger.info("flag1");
                int otp = 100000 + rand.nextInt(900000);
                otpService.AddOtp(otp,"ChangePassword", foundUser.getEmailAddress());
                EmailObject emailObject = new EmailObject();
                emailObject.setRecipient(foundUser.getEmailAddress());
                emailObject.setOtp(otp);
                emailObject.setMessage("We would like to send you otp:");
                kafkaTemplate.send("notification",emailObject);
                responseObject.setMessage("Send email successfully!");
                responseObject.setChangeSuccessfully(false);
            }
            else if (otpEntityRepository.findByUserEmail(foundUser.getEmailAddress()) != null && changePasswordRequest.getOtp() != 0)
            {
                    if(changePasswordRequest.getOtp() == otpEntityRepository.findByUserEmail(foundUser.getEmailAddress()).getOtp())
                    {
                        foundUser.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
                        userRepository.save(foundUser);
                        otpEntityRepository.delete(otpEntityRepository.findByUserEmail(foundUser.getEmailAddress()));
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
            User ourUser = userRepository.findByUserId(changeInfoRequest.getUserID()).orElseThrow();
            logger.info("Test2"+ourUser.getEmailAddress());
            logger.info(token);
            logger.info(changeInfoRequest.getPhoneNumber());
            logger.info(changeInfoRequest.getFacebook());
            logger.info(changeInfoRequest.getUsername());
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
//            if(userRepository.existUserByEmailAddress(changeInfoRequest.getEmailAddress()) && !Objects.equals(changeInfoRequest.getEmailAddress(), ourUser.getEmailAddress())) {
//                errors.add(new ErrorDTO("DuplicatedGmail","Email address is taken!"));
//                isExisted=true;
//            }
            if(changeInfoRequest.getPhoneNumber().length() != 10)
            {
                errors.add(new ErrorDTO("PhonenumberFormat","Phone number is invalid!"));
                isExisted=true;
            }
//            if(!patternMatches(changeInfoRequest.getEmailAddress(), "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
//            {
//                errors.add(new ErrorDTO("GmailFormat","Gmail address is invalid!"));
//                isExisted=true;
//            }

            if(isExisted)
            {
                responseObject.setChangeSuccessfully(false);
                responseObject.setErrors(errors);
                responseObject.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
            }
            else
            {
                    User foundUser = userRepository.findByUserId(changeInfoRequest.getUserID()).orElseThrow();
                    logger.info("Test1"+foundUser.getEmailAddress());
                    UpdateUserCommandObject updateUserCommandObject = new UpdateUserCommandObject(changeInfoRequest.getUserID(),changeInfoRequest.getFirstName(),changeInfoRequest.getLastName(),changeInfoRequest.getPhoneNumber(),changeInfoRequest.getUsername(),changeInfoRequest.getFacebook(),changeInfoRequest.getApple());
                    commandGateway.sendAndWait(updateUserCommandObject);
                    responseObject.setMessage("Change user information successfully!");
                    responseObject.setChangeSuccessfully(true);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while changing user information: " + e.getMessage());
            responseObject.setChangeSuccessfully(false);
        }
        return responseObject;
    }


}
