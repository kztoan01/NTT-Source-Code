package com.NTT.NTTTeam.Query.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDTO {

    private String userId;

    private String firstName;


    private String lastName;


    private String phoneNumber;


    private String emailAddress;


    private String userName;

    private String facebook;


    private String apple;


    private Boolean activeStatus;


    private Integer userRole;
}
