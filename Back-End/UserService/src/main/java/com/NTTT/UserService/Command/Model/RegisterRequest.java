package com.NTTT.UserService.Command.Model;

import jakarta.persistence.Column;
import lombok.Data;


@Data
public class RegisterRequest {


    private String phoneNumber;

    private String userName;

    private String password;

    private Integer userRole;

    private String gmail;

}
