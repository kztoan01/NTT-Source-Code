package com.NTTT.UserService.Command.Data;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OtpTable")
public class OtpEntity {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userEmail",nullable = false)
    private String userEmail;

    @Column(name = "otpType",nullable = false)
    private String otpType;


    @Column(name = "otp",nullable = false)
    private Integer otp;


}
