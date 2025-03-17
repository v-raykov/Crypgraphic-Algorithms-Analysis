package com.dreamteam.algorithm.analysis.web.controller;

import com.dreamteam.algorithm.analysis.model.User;
import com.dreamteam.algorithm.analysis.model.dto.RegisterDto;
import com.dreamteam.algorithm.analysis.model.dto.LoginDto;
import com.dreamteam.algorithm.analysis.web.service.JwtService;
import com.dreamteam.algorithm.analysis.web.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String home() {
        return "Hello World";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto details) {
        return authenticateUser(details);
    }

    @PostMapping("/register")
    public User register(@Valid @RequestBody RegisterDto details) {
        return userService.registerUser(details);
    }

    private String authenticateUser(LoginDto details) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(details.getUsername(), details.getPassword()));
        userService.loadUserByUsername(details.getUsername());
        return jwtService.generateToken(details.getUsername());
    }
}
