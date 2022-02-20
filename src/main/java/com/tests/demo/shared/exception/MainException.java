package com.tests.demo.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MainException extends RuntimeException{

    public MainException(String message){
        super(message);

    }
}
