package com.voting.Votingapp.security;

import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@ToString
public class CustomUserDetails implements UserDetails {

    private String username;

    private String pfpurl;

    public String getPfpurl() {
        return pfpurl;
    }

    public void setPfpurl(String pfpurl) {
        this.pfpurl = pfpurl;
    }

    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


public CustomUserDetails(){

}

    public CustomUserDetails(String username, String email, String password,String pfpurl, Collection<? extends GrantedAuthority> authorities) {

        this.username = username;
        this.email = email;
        this.password = password;
        this.pfpurl= pfpurl;
        this.authorities = authorities;
    }





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
