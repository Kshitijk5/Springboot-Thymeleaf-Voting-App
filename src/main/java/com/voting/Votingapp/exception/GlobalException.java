package com.voting.Votingapp.exception;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalException {



    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String imageMaxSize(MaxUploadSizeExceededException ex, Model model){
        model.addAttribute("errormsg","Add image less than 5MB, password reset failed.");
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

    @ExceptionHandler(NoHandlerFoundException.class)
    public String noHandler(NoHandlerFoundException ex){
        System.out.println(ex);
        return "access-denied";
    }
    @ExceptionHandler(Exception.class)
    public String globalErrorHandler(Exception ex){
        System.out.println(ex);

        return "Error-Page";
    }
}
