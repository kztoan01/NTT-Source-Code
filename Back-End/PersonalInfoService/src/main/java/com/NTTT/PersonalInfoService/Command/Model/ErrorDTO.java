package com.NTTT.PersonalInfoService.Command.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {
    private String Type;

    private String Message;
}
