package com.voting.Votingapp.controller;

import com.voting.Votingapp.model.User;
import com.voting.Votingapp.repository.UserRepository;
import com.voting.Votingapp.security.CustomUserDetails;
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
    private ServletContext servletContext;

    //   public final String resource = new ClassPathResource("/static/assets/Images/profile").getFile().getAbsolutePath();
    public Controller() throws IOException {

    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/home")
    public String home(@ModelAttribute("updatestatus")String data, Model model) {
//        String profileUrl = userRepository.findByUsername(principal.getName()).get().getProfileUrl();

//        UserDetails user = (UserDetails) principal;
//        System.out.println(user);
//

//        if(profileUrl==null||profileUrl=="")
//             model.addAttribute("pfpurl",(ServletUriComponentsBuilder.fromCurrentContextPath().path("/assets/Images/").path("man.png").toUriString()));
//        else
//            model.addAttribute("pfpurl",profileUrl);
//        String vl = data;
//        System.out.println("This is the  model value- "+ vl);
        return "home";
    }

    @GetMapping("/admin-panel")
    public String adminPanel() {
        return "admin-panel";
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
//        ModelAndView modelAndView = new ModelAndView("home");
        String username = principal.getName();

        User user = userRepository.findByUsername(username).get();
//        String UPLOAD_DIR = new ClassPathResource("//static/assets/Images/profile/").getFile().getAbsolutePath();

        UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
//        System.out.println(userDetails.getPassword());/**/
        System.out.println(userDetails);
        System.out.println("Current password entered by user:- " + currentPassword);
        System.out.println("logged in password:- " + userDetails.getPassword());
        // Get the current authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (passwordEncoder.matches(currentPassword, userDetails.getPassword())) {
            System.out.println(pfp.getContentType());
            if (!pfp.isEmpty() && ((pfp.getContentType().equals("image/jpeg") || (pfp.getContentType().equals("image/png"))))) {

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
//                        ;username
//                        email
//                        password
//                                url
//                                auth

// Create a new authentication object with the custom UserDetails object
                Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                        userDetailsnew, userDetailsnew.getPassword(), userDetailsnew.getAuthorities());

// Set the new authentication object as the current authentication
                SecurityContextHolder.getContext().setAuthentication(newAuthentication);


                //model.addAttribute("updatestatus", "alldetails");

                redirectAttributes.addFlashAttribute("updatestatus", "alldetails");

                return "redirect:/home";
            } else {
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
          //  model.addAttribute("updatestatus", "passerror");
            redirectAttributes.addFlashAttribute("updatestatus", "passerror");
            return "redirect:/home";
        }


    }

}
