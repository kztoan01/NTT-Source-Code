package com.ntt.nttsearchfoodservice.exception;

import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class ExceptionControl {
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        System.out.println(ex.getMessage());
        return ResponseEntity.status(404).body("Data not found");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception e) {
        System.out.println(e.getMessage());
        return ResponseEntity.status(500).body("Unknown Error");
    }

    @ExceptionHandler(UnsatisfiedDependencyException.class)
    public ResponseEntity<String> unsatisfiedDependencyException(UnsatisfiedDependencyException ex) {
        System.out.println(ex.getMessage());
        return ResponseEntity.status(500).body("Elasticsearch Error");
    }

    @ExceptionHandler(UploadException.class)
    public ResponseEntity<String> uploadException(UploadException ex) {
        System.out.println(ex.getMessage());
        return ResponseEntity.status(500).body("Upload Error");
    }
}
