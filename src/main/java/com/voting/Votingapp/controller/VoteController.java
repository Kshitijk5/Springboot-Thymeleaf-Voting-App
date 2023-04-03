package com.voting.Votingapp.controller;

import com.voting.Votingapp.service.impl.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("/")
    public String voteHandler(@RequestParam("partyname") String partyShortName,
                              Principal principal,
                              RedirectAttributes redirectAttributes,
                              Model model

    ) {

        Boolean result = voteService.doVote(principal.getName(), partyShortName);
        if (result) {
            redirectAttributes.addFlashAttribute("votestatus", true);
            return "redirect:/home";
        } else {
            redirectAttributes.addFlashAttribute("votestatus", false);
            return "redirect:/home";

        }
    }

}
