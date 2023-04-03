package com.voting.Votingapp;

import com.voting.Votingapp.model.Role;
import com.voting.Votingapp.model.User;
import com.voting.Votingapp.repository.RoleRepository;
import com.voting.Votingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication

public class VotingAppApplication /*implements CommandLineRunner*/ {

    public static void main(String[] args) {
        SpringApplication.run(VotingAppApplication.class, args);
    }

    @Bean
    public ResourceLoader resourceLoader() {
        return new DefaultResourceLoader();
    }


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
    @Override
    public void run(String... args) throws Exception {
        //Roles add
        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        Role userRole = new Role();
        userRole.setName("ROLE_USER");

        roleRepository.save(adminRole);
        roleRepository.save(userRole);

        User user = new User();
        user.setUsername("kshitij");
        user.setPassword(passwordEncoder.encode("1"));
        user.setEmail("kshitij@gmail.com");
        user.setVoted(false);

        Role temp = roleRepository.findByName("ROLE_ADMIN").get();
        Set<Role> role = new HashSet<>();
        role.add(temp);
        user.setRoles(role);
        userRepository.save(user);
       }
*/

}
