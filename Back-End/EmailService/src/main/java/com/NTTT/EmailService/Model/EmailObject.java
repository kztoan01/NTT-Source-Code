package com.NTTT.EmailService.Model;


import lombok.Data;

@Data
public class EmailObject {
    private String recipient;

    private String message;

    private Integer otp;
}
