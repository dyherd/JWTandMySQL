package com.jwtandsql.controller;

import com.jwtandsql.model.JwtRequest;
import com.jwtandsql.model.JwtResponse;
import com.jwtandsql.model.User;
import com.jwtandsql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController() {
    }
    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createJwtToken(@RequestBody User user) throws Exception {
        return userService.create(user);
    }
    @GetMapping("hello")
    public String hello() {
        return "Okay";
    }
}
