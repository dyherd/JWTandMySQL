package com.jwtandsql.service;

import com.jwtandsql.model.JwtRequest;
import com.jwtandsql.model.JwtResponse;
import com.jwtandsql.model.User;
import com.jwtandsql.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public JwtResponse getJwtToken(JwtRequest jwtRequest) {
        UserDetails userDetails = loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails.getUsername());
        User user = userService.findByUsername(jwtRequest.getUsername());
        return new JwtResponse(user, token);
    }

}
