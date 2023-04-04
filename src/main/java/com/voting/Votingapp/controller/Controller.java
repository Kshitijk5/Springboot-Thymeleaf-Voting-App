package com.voting.Votingapp.controller;

import com.voting.Votingapp.model.User;
import com.voting.Votingapp.repository.UserRepository;
import com.voting.Votingapp.security.CustomUserDetails;
import com.voting.Votingapp.service.impl.VoteService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

@org.springframework.stereotype.Controller
@RequestMapping
public class Controller {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private VoteService voteService;


    @Autowired
    private ServletContext servletContext;

    
    public Controller() throws IOException {

    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping( value = {"/home","/"})
    public String home(@ModelAttribute("updatestatus")String updateStatus,@ModelAttribute("votestatus") String voteStatus, Model model) {
        System.out.println("vote status:- "+ voteStatus);
        System.out.println("update status:- "+ updateStatus);
        return "home";
    }

    @GetMapping("/admin-panel")
    public String adminPanel(Model model) {
        model.addAttribute("voteData", voteService.getVoteCountsForAllParties());
        model.addAttribute("userdetails",userRepository.findAll());
        return "admin-panel";
    }

    @GetMapping("/access-denied")
    public String accessdenied(Model model) {

        return "access-denied";
    }

 @GetMapping("/error")
 public String error( ) {

     return "Error-Page";
 }


    @PostMapping("/update-profile")
    public String updateProfile(
            @RequestParam(value = "pfp", required = false) MultipartFile pfp,
            @RequestParam(value = "username", required = true) String name,
            @RequestParam(value = "pass", required = true) String currentPassword,
            @RequestParam(value = "newpass", required = true) String newPassword,
            Principal principal,
            RedirectAttributes redirectAttributes,
            Model model

    ) throws IOException {

        String username = principal.getName();

        User user = userRepository.findByUsername(username).get();


        UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();

        System.out.println(userDetails);
        System.out.println("Current password entered by user:- " + currentPassword);
        System.out.println("logged in password:- " + userDetails.getPassword());
        // Get the current authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (passwordEncoder.matches(currentPassword, userDetails.getPassword())) {
            System.out.println(pfp.getContentType());
            if (!pfp.isEmpty() && ((pfp.getContentType().equals("image/jpeg") || (pfp.getContentType().equals("image/png"))))) {
                System.out.println(pfp.getSize()+"Is the size");
                if(newPassword==null || newPassword.isEmpty() || newPassword==""||name==""|| name==null|| name.isEmpty()) {
                    redirectAttributes.addFlashAttribute("updatestatus", "noupdate");
                    return "redirect:/home";
                }
                 if(pfp.getSize() > 5242880) {
                     model.addAttribute("errormsg","Add image less than 5MB, password reset failed.");
                     return "Error-Page";
                 }
                user.setProfilePic(pfp.getOriginalFilename());
                String resource = new ClassPathResource("/static/assets/Images/").getFile().getAbsolutePath();


                Files.copy(pfp.getInputStream(), Paths.get(resource + File.separator + pfp.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                user.setProfileUrl((ServletUriComponentsBuilder.fromCurrentContextPath().path("/assets/Images/").path(pfp.getOriginalFilename())).toUriString());
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setUsername(name);
                userRepository.save(user);



// Create a new custom UserDetails object
                CustomUserDetails userDetailsnew = new CustomUserDetails(
                        username,
                        user.getEmail(),
                        passwordEncoder.encode(newPassword),
                        user.getProfileUrl(),
                        authentication.getAuthorities()


                );
                System.out.println(userDetailsnew);


// Create a new authentication object with the custom UserDetails object
                Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                        userDetailsnew, userDetailsnew.getPassword(), userDetailsnew.getAuthorities());

// Set the new authentication object as the current authentication
                SecurityContextHolder.getContext().setAuthentication(newAuthentication);


              

                redirectAttributes.addFlashAttribute("updatestatus", "alldetails");

                return "redirect:/home";
            } else {
                if(!StringUtils.hasText(newPassword) || !StringUtils.hasText(name)) {
                    redirectAttributes.addFlashAttribute("updatestatus", "noupdate");
                    return "redirect:/home";
                }
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setUsername(name);
                userRepository.save(user);
                redirectAttributes.addFlashAttribute("updatestatus", "justdetails");

                CustomUserDetails userDetailsnew = new CustomUserDetails(
                        user.getUsername(),
                        user.getEmail(),
                        passwordEncoder.encode(newPassword),
                        user.getProfileUrl(),
                        authentication.getAuthorities()


                );

                Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                        userDetailsnew, userDetailsnew.getPassword(), userDetailsnew.getAuthorities());

// Set the new authentication object as the current authentication
                SecurityContextHolder.getContext().setAuthentication(newAuthentication);

                return "redirect:/home";
            }
        } else {
          
            redirectAttributes.addFlashAttribute("updatestatus", "passerror");
            return "redirect:/home";
        }


    }

}
