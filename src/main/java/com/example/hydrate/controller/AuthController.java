package com.example.hydrate.controller;

import com.example.hydrate.model.jwt.AuthenticationRequest;
import com.example.hydrate.model.jwt.AuthenticationResponse;
import com.example.hydrate.service.MyUserDetailsService;
import com.example.hydrate.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    MyUserDetailsService userDetailsService;

    @RequestMapping(value="/authenticate",method = RequestMethod.POST)
    public ResponseEntity<?> createToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
       final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
       final String jwt= jwtUtil.generateToken(userDetails);
       return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }
}
