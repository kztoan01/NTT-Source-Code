package com.NTTT.UserService.Command.Model;


import lombok.Data;

@Data
public class SendPhoneOtpRequest {

    private String phoneNumber;

    public SendPhoneOtpRequest() {
    }

    public SendPhoneOtpRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
