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
@Table(name = "otp")
public class Otp {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userId",nullable = false)
    private int userId;

    @Column(name = "otpType",nullable = false)
    private String otpType;


    @Column(name = "otp",nullable = false)
    private Integer otp;


}
