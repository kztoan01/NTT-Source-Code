package com.NTTT.PersonalInfoService.Command.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseObject<T> {
    private int statusCode;
    private List<ErrorDTO> errors = new ArrayList<>();
    private String message;
    private boolean isChangeSuccessfully;
    private T data;
}
