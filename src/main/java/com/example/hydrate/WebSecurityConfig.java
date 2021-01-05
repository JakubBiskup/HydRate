package com.example.hydrate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/water").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/reviews").hasAnyRole("REVIEWER","ADMIN")
                .antMatchers(HttpMethod.GET,"/**").permitAll()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();
    }


}
