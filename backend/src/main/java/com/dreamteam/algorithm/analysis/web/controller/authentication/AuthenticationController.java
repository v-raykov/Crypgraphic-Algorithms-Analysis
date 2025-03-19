package com.dreamteam.algorithm.analysis.web.controller.authentication;

import com.dreamteam.algorithm.analysis.model.User;
import com.dreamteam.algorithm.analysis.model.dto.LoginDto;
import com.dreamteam.algorithm.analysis.model.dto.RegisterDto;
import com.dreamteam.algorithm.analysis.model.response.JwtToken;
import com.dreamteam.algorithm.analysis.web.service.authentication.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<JwtToken> login(@RequestBody LoginDto details) {
        return ResponseEntity.ok().body(authenticationService.loginUser(details));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterDto details) {
        details.setPassword(passwordEncoder.encode(details.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.registerUser(details));
    }
}
