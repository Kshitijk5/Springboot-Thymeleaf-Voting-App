package com.voting.Votingapp.controller;

import com.voting.Votingapp.model.Role;
import com.voting.Votingapp.model.User;
import com.voting.Votingapp.repository.RoleRepository;
import com.voting.Votingapp.repository.UserRepository;
import com.voting.Votingapp.utils.AppConstants;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userModel", new User());
        return "register";
    }

    @PostMapping("/register-process")
    public ModelAndView registerProcessor(@ModelAttribute("userModel") User user) {
        System.out.println(user);
        ModelAndView modelAndView = new ModelAndView("register");
        // add check for username exists in database
        if (userRepository.existsByUsername(user.getUsername())) {
//            modelAndView.setViewName("register");
            modelAndView.addObject(AppConstants.REGISTER_STATUS, AppConstants.REGISTER_USERNAME_EXISTS);

            return modelAndView;
        }

        // add check for email exists in database
        if (userRepository.existsByEmail(user.getEmail())) {
//            modelAndView.setViewName("register");
            modelAndView.addObject(AppConstants.REGISTER_STATUS, AppConstants.REGISTER_EMAIL_EXISTS);
            return modelAndView;
        }

        User newUser = new User();

        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setProfileUrl((ServletUriComponentsBuilder.fromCurrentContextPath().path("/assets/Images/").path(AppConstants.DEFAULT_PFP).toUriString()));
        newUser.setVoted(false);

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        newUser.setRoles(roles);

        userRepository.save(newUser);

//        modelAndView.setViewName("register");
        modelAndView.addObject(AppConstants.REGISTER_STATUS,AppConstants.REGISTER_SUCCESS);

        return modelAndView;
    }
}

