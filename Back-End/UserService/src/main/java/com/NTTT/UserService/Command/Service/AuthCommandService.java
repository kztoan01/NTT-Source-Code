package com.NTTT.UserService.Command.Service;

import com.NTTT.UserService.Command.Data.OtpEntityRepository;
import com.NTTT.UserService.clients.EmailClient;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.NTTT.UserService.Command.Command.CreateUserCommandObject;
import com.NTTT.UserService.Command.Controller.AuthCommandController;
import com.NTTT.UserService.Command.Data.User;
import com.NTTT.UserService.Command.Data.UserRepository;
import com.NTTT.UserService.Command.Model.*;
import com.NTTT.UserService.Query.Model.ResponseUserDTO;

import java.util.*;
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
    OtpService otpService;

    @Autowired
    EmailClient emailClient;
    Random rand = new Random();


    Logger logger
            = LoggerFactory.getLogger(AuthCommandController.class);

    @Autowired
    OtpEntityRepository otpEntityRepository;

    @Autowired
    private ModelMapper modelMapper;
    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public ResponseObject signUpWithEmail(RegisterRequest registrationRequest)
    {
        ResponseObject responseObject = new ResponseObject();
        try {
            boolean isExisted = false;
            List<ErrorDTO> errors = new ArrayList<>();
            if(userRepository.existUserByEmailAddress(registrationRequest.getGmail())) {
                errors.add(new ErrorDTO("DuplicatedGmail","Email address is taken!"));
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
                if( otpEntityRepository.findByUserEmail(registrationRequest.getGmail()) == null && registrationRequest.getOtp() == 0)
                {
                    int otp = 100000 + rand.nextInt(900000);
                    otpService.AddOtp(otp,"VerifyEmail", registrationRequest.getGmail());
                    emailClient.sendMail("Changing password otp","Hi,We would like to send you OTP:"+otp,registrationRequest.getGmail());
                    responseObject.setMessage("Send email successfully!");
                    responseObject.setChangeSuccessfully(false);
                }
                else if(otpEntityRepository.findByUserEmail(registrationRequest.getGmail()) != null && registrationRequest.getOtp() != 0)
                {
                    logger.info("test");
                    String id = UUID.randomUUID().toString();
                    CreateUserCommandObject command =
                            new CreateUserCommandObject(id,null,null,null,registrationRequest.getGmail(),null, null, null,null,false,1);
                    commandGateway.sendAndWait(command);
                    otpEntityRepository.delete(otpEntityRepository.findByUserEmail(registrationRequest.getGmail()));
                    responseObject.setMessage(id);
                    responseObject.setChangeSuccessfully(true);
                    responseObject.setStatusCode(HttpStatus.CREATED.value());
                }
            }
        } catch (DataAccessException e) {
            responseObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseObject;
    }


    public ResponseObject changeUserPasswordFirstTime(changePasswordRequest changePasswordRequest) {
        ResponseObject responseObject = new ResponseObject();
        try {
            User foundUser = userRepository.findByUserId(changePasswordRequest.getUserId()).orElseThrow();

            if(foundUser.getEmailAddress() != null)
                {
                    foundUser.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
                    userRepository.save(foundUser);
                    responseObject.setMessage("Change Password successfully!");
                    responseObject.setChangeSuccessfully(true);
                }
                else
                {
                    responseObject.setMessage("Change Password failed.User not found!");
                    responseObject.setChangeSuccessfully(false);
                }
        } catch (Exception e) {
            System.out.println("An error occurred while changing the password: " + e.getMessage());
        }
        return responseObject;
    }

    public ResponseObject changeUserInfoFirstTime(RegisterRequest registerRequest) {
        ResponseObject responseObject = new ResponseObject();
        try {
            User foundUser = userRepository.findByEmailAddress(registerRequest.getGmail()).orElseThrow();

            if(foundUser.getEmailAddress() != null)
            {
                foundUser.setUserName(registerRequest.getUserName());
                foundUser.setPhoneNumber(registerRequest.getPhoneNumber());
                userRepository.save(foundUser);
                responseObject.setMessage("Change Password successfully!");
                responseObject.setChangeSuccessfully(true);
            }
            else
            {
                responseObject.setMessage("Change Password failed.User not found!");
                responseObject.setChangeSuccessfully(false);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while changing the password: " + e.getMessage());
        }
        return responseObject;
    }

//    public ResponseObject signUp(RegisterRequest registrationRequest){
//        ResponseObject responseObject = new ResponseObject();
//        try {
//            boolean isExisted = false;
//            List<ErrorDTO> errors = new ArrayList<>();
//                if(userRepository.existUserByPhoneNumber(registrationRequest.getPhoneNumber())) {
//                    errors.add(new ErrorDTO("DuplicatedPhonenumber","Phone number is registered!"));
//                    isExisted=true;
//                }
//                if(userRepository.existsUserByUserName(registrationRequest.getUserName())) {
//                    errors.add(new ErrorDTO("DuplicatedUsername","User name is taken!"));
//                    isExisted=true;
//                }
//                if(userRepository.existUserByEmailAddress(registrationRequest.getGmail())) {
//                    errors.add(new ErrorDTO("DuplicatedGmail","Email address is taken!"));
//                    isExisted=true;
//                }
//                if(registrationRequest.getPhoneNumber().length() != 10)
//                {
//                    errors.add(new ErrorDTO("PhonenumberFormat","Phone number is invalid!"));
//                    isExisted=true;
//                }
//                if(!patternMatches(registrationRequest.getGmail(), "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
//                {
//                    errors.add(new ErrorDTO("GmailFormat","Gmail address is invalid!"));
//                    isExisted=true;
//                }
//
//                if(isExisted)
//                {
//                    responseObject.setChangeSuccessfully(false);
//                    responseObject.setErrors(errors);
//                    responseObject.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
//                }
//                else {
//                    CreateUserCommandObject command =
//                            new CreateUserCommandObject(UUID.randomUUID().toString(),null,null,registrationRequest.getPhoneNumber(),registrationRequest.getGmail(),registrationRequest.getUserName(), passwordEncoder.encode(registrationRequest.getPassword()), null,null,false,1);
//                    commandGateway.sendAndWait(command);
//                responseObject.setMessage("Register successfully!");
//                responseObject.setChangeSuccessfully(true);
//                responseObject.setStatusCode(HttpStatus.CREATED.value());
//                }
//        } catch (DataAccessException e) {
//            responseObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            logger.info("AuthService:" + e.getMessage());
//        }
//        return responseObject;
//    }


    public ResponseObject signIn(AuthenticationRequest signinRequest){
        ResponseObject response = new ResponseObject();

        try {
            // Manually authenticate user
            User user = userRepository.findByEmailAddress(signinRequest.getGmail()).orElse(null);
            if(user != null && user.getPassword().equals(signinRequest.getPassword())) {
                logger.info("USER IS:" + user);
                var jwt = jwtUtils.generateToken(user);
                var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshToken);
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully Signed In");
                response.setResponseUserDTO(modelMapper.map(user, ResponseUserDTO.class));
            } else {
                response.setStatusCode(404);
                response.getErrors().add(new ErrorDTO("LoginFalse","Username or password is incorrect!"));
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.getErrors().add(new ErrorDTO("Login error",e.getMessage()));
        }
        return response;
    }

    public ResponseObject refreshToken(ResponseObject refreshTokenRequest){
        ResponseObject response = new ResponseObject();
        String ourUsername = jwtUtils.extractUsername(refreshTokenRequest.getToken());
        User users = userRepository.findByUserName(ourUsername).orElseThrow();
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
