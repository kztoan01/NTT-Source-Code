package com.NTT.NTTTeam.Command.service;


import com.NTT.NTTTeam.Command.Data.Otp;
import com.NTT.NTTTeam.Command.Data.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
