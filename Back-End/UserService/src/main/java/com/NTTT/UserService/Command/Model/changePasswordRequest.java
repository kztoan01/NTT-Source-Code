package com.NTTT.UserService.Command.Model;

import lombok.Data;

import java.util.UUID;


@Data
public class changePasswordRequest {
    private String newPassword;

    private String userId;


    private int otp;

}
