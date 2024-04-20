package com.NTTT.UserService.Command.Model;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class changeInfoRequest {

    private String userID;

    @Size(min = 0,max = 10,message = "Username must between 0 and 10 characters")
    private String username;

    private String apple;

    private String facebook;


    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String emailAddress;

    @Size(min = 10,max = 10,message = "Invalid phone number")
    private String phoneNumber;


    private String lastName;

    private String firstName;

}
