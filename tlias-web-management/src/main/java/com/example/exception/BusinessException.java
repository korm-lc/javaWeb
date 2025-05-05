package com.example.exception;

import lombok.Data;


public class BusinessException extends RuntimeException{

    private String message;
    public BusinessException(){
        super();
    }

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message,Throwable cause){
        super(message, cause);
    }
}
