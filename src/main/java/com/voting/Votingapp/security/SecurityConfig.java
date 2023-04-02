package com.voting.Votingapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public DefaultSecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**").permitAll()
//                        .requestMatchers("/home").permitAll()
                        .requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/assets/Images/**","/assets/videos/**","/assets/Images/profile-pics/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formlogin -> formlogin
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/process")

                        .defaultSuccessUrl("/home",true)
                )
                .logout()
                .logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/auth/login");


        http.formLogin();


        return http.build();
    }

   /* @Bean
    public InMemoryUserDetailsManager getInMemoryUserDetailsManager() {
        UserDetails user1 = User.builder()
                .username("kshitij")
                .password(getPasswordEncoder().encode("123"))
                .roles("ADMIN")
                .build();
        UserDetails user2 = User.builder()
                .username("scout")
                .password(getPasswordEncoder().encode("123"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user1);
    }*/

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return  authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().dispatcherTypeMatchers("/css/**");
//    }

}
