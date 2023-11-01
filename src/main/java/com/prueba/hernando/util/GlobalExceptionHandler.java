package com.prueba.hernando.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StandarErrorMessage.class)
    public ResponseEntity<Object> handelerStandarMessage(StandarErrorMessage standarErrorMessage){
        Map<String,Object> body= new LinkedHashMap<>();
        body.put("message", standarErrorMessage.getMessage());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValidationCustomer.class)
    public ResponseEntity<Object> handlerValidationCustomer(ValidationCustomer validationCustomer){
        Map<String,Object> body= new LinkedHashMap<>();
        body.put("message",validationCustomer.getMessage());
        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }
}
