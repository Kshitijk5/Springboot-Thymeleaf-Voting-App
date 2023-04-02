package com.voting.Votingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/vote")
public class VoteController {


    @PostMapping("/{party_id}")
    public String voteHandler(@PathVariable("party_id") long id,
                              Principal principal,
                              RedirectAttributes redirectAttributes,
                              Model model
                              ){

    return "home";
    }

}
