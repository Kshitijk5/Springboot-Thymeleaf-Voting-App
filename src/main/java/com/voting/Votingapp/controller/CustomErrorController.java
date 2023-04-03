package com.voting.Votingapp.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "Error-Page"; // return the name of your custom 404 error page
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "Error-Page"; // return the name of your custom 500 error page
            }
        }
        return "error"; // return the name of your default error page
    }

//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
}
