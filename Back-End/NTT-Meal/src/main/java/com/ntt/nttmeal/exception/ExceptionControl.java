package com.ntt.nttmeal.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControl {
    @ExceptionHandler(Exception.class)
    public String exception(Exception e) {
        System.out.println(e.getMessage());
        return "Unknown Exception";
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String httpMessageNotReadableException(HttpMessageNotReadableException e) throws Exception {
        System.out.println(e.getMessage());
        if (e.getMessage().contains("Required request body is missing:")) return "Body is missing";
        else throw new Exception();
    }

    @ExceptionHandler(NotFoundDataException.class)
    public String notFoundDataException(NotFoundDataException e) throws Exception {
        System.out.println(e.getMessage());
        return "Id Not Found";
    }
}
