package com.NTTT.UserService.Validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.NTTT.UserService.Command.Controller.AuthCommandController;
import com.NTTT.UserService.Command.Model.ErrorDTO;
import com.NTTT.UserService.Command.Model.ResponseObject;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(AuthCommandController.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseObject validationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        List<ErrorDTO> errors = new ArrayList<>();
        for (FieldError error : fieldErrors) {
            String defaultMessage = error.getDefaultMessage();
            System.out.println("defaultMessage: " + defaultMessage);
            if(defaultMessage.contains("Email"))
            {
                errors.add(new ErrorDTO("EmailFormat",defaultMessage));
            }
            if(defaultMessage.contains("phone"))
            {
                errors.add(new ErrorDTO("PhoneNumberFormat",defaultMessage));
            }
            if(defaultMessage.contains("Username"))
            {
                errors.add(new ErrorDTO("UsernameFormat",defaultMessage));
            }
        }
        ResponseObject responseObject = new ResponseObject();
        responseObject.setErrors(errors);
        return responseObject;
    }
}

