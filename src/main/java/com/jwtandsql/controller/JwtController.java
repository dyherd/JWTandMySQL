package com.jwtandsql.controller;


import com.jwtandsql.model.JwtRequest;
import com.jwtandsql.model.JwtResponse;
import com.jwtandsql.model.User;
import com.jwtandsql.service.JwtService;
import com.jwtandsql.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/")
public class JwtController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        return jwtService.getJwtToken(jwtRequest);
    }

    private void authenticate(String username, String userPassword) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, userPassword));
        } catch (BadCredentialsException be) {
            throw new RuntimeException("Invalid credentials", be);
        }
    }
}

