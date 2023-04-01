package com.voting.Votingapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping
public class Controller {

    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
