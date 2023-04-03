package com.voting.Votingapp.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class GlobalException {



    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String imageMaxSize(MaxUploadSizeExceededException ex){
        System.out.println(ex);
        return "Error-Page";
    }
    @ExceptionHandler(MultipartException.class)
    public String errorHandler(MultipartException ex){
        System.out.println(ex);
        return "Error-Page";
    }


    @ExceptionHandler(AccessDeniedException.class)
    public String accessDeniedHandler(AccessDeniedException ex){
        System.out.println(ex);
        return "access-denied";
    }

    @ExceptionHandler(Exception.class)
    public String globalErrorHandler(Exception ex){
        System.out.println(ex);
        return "Error-Page";
    }
}
