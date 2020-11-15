package com.hausen.shiro2.utils;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public String doException(Exception e){

        if(e instanceof AuthorizationException){
            return "lesspermission";
        }

        return null;
    }
}
