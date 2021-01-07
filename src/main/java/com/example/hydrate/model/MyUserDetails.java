package com.example.hydrate.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> role;

    public MyUserDetails(User user){
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.active=user.isActive();
        SimpleGrantedAuthority authority=new SimpleGrantedAuthority(user.getRole());
        List<GrantedAuthority> authorityList=new ArrayList<GrantedAuthority>();
        authorityList.add(authority);
        this.role=authorityList;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
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
        return active;
    }
}
