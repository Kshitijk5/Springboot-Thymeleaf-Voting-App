package com.voting.Votingapp;

import com.voting.Votingapp.model.Party;
import com.voting.Votingapp.model.Role;
import com.voting.Votingapp.model.User;
import com.voting.Votingapp.repository.PartyRepository;
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

public class VotingAppApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(VotingAppApplication.class, args);
    }


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        //Roles add
        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleRepository.save(userRole);

        //Creating ADMIN
        User user = new User();
        user.setUsername("kshitij");
        user.setPassword(passwordEncoder.encode("123"));
        user.setEmail("kshitij@gmail.com");
        user.setVoted(false);

        Role temp = roleRepository.findByName("ROLE_ADMIN").get();
        Set<Role> roles = new HashSet<>();
        roles.add(temp);

        //Setting Roles and saving ADMIN
        user.setRoles(roles);
        userRepository.save(user);

        //Add party
        Party party1 = new Party();
        party1.setPartyName("The People's Voice Party");
        party1.setPartyShortName("PVP");
        partyRepository.save(party1);

        Party party2 = new Party();
        party2.setPartyName("The Unity Party");
        party2.setPartyShortName("UP");
        partyRepository.save(party2);

        Party party3 = new Party();
        party3.setPartyName("The Progressive Alliance");
        party3.setPartyShortName("PA");
        partyRepository.save(party3);

        Party party4 = new Party();
        party4.setPartyName("The Freedom Coalition");
        party4.setPartyShortName("FC");
        partyRepository.save(party4);


       }


}
