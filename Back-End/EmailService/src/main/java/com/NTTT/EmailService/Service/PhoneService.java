package com.NTTT.EmailService.Service;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import com.NTTT.EmailService.Model.SendPhoneOtpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.twilio.rest.verify.v2.service.Verification;

@Service
public class PhoneService {

    Logger logger = LoggerFactory.getLogger(PhoneService.class);

    @KafkaListener(id = "otp",topics = "otp")
    public void sendPhoneOtp(SendPhoneOtpRequest sendPhoneOtpRequest)
    {
        String phoneNumber = sendPhoneOtpRequest.getPhoneNumber();
        logger.info("Catched:"+"+84" + phoneNumber);
        Twilio.init("AC3df64099f9254d513d2189b0c9ff7311", "083c4ebf30842727f51df046d014a7da");
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("+84342764987"),
                        new com.twilio.type.PhoneNumber("+13253356964"),
                        "Otp")
                .create();

        System.out.println(message.getSid());
    }
}

