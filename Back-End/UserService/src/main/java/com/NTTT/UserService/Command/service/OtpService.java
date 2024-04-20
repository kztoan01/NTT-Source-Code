package com.NTTT.UserService.Command.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NTTT.UserService.Command.Data.Otp;
import com.NTTT.UserService.Command.Data.OtpRepository;

@Service
public class OtpService {

    @Autowired
    OtpRepository otpRepository;

    public void AddOtp(int otp,String optType,int userId)
    {
        Otp otp1 = new Otp();
        otp1.setOtpType(optType);
        otp1.setOtp(otp);
        otp1.setUserId(userId);
        otpRepository.save(otp1);
    }
}
