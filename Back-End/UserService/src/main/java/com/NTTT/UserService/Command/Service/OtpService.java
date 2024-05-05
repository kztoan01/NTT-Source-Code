package com.NTTT.UserService.Command.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NTTT.UserService.Command.Data.OtpEntity;
import com.NTTT.UserService.Command.Data.OtpEntityRepository;

@Service
public class OtpService {

    @Autowired
    OtpEntityRepository otpEntityRepository;

    public void AddOtp(int otp,String optType,String userEmail)
    {
        OtpEntity otpEntity1 = new OtpEntity();
        otpEntity1.setOtpType(optType);
        otpEntity1.setOtp(otp);
        otpEntity1.setUserEmail(userEmail);
        otpEntityRepository.save(otpEntity1);
    }
}
