package com.dreamteam.algorithm.analysis.web.controller.authentication;

import com.dreamteam.algorithm.analysis.model.User;
import com.dreamteam.algorithm.analysis.model.dto.LoginDto;
import com.dreamteam.algorithm.analysis.model.dto.RegisterDto;
import com.dreamteam.algorithm.analysis.web.service.authentication.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody LoginDto details) {
        return authenticationService.loginUser(details);
    }

    @PostMapping("/register")
    public User register(@Valid @RequestBody RegisterDto details) {
        details.setPassword(passwordEncoder.encode(details.getPassword()));
        return authenticationService.registerUser(details);
    }
}
