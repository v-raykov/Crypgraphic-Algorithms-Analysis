package com.dreamteam.algorithm.analysis.web.service.authentication;

import com.dreamteam.algorithm.analysis.config.exception.UsernameExistsException;
import com.dreamteam.algorithm.analysis.config.security.JwtUtils;
import com.dreamteam.algorithm.analysis.config.security.role.Role;
import com.dreamteam.algorithm.analysis.model.User;
import com.dreamteam.algorithm.analysis.model.dto.LoginDto;
import com.dreamteam.algorithm.analysis.model.dto.RegisterDto;
import com.dreamteam.algorithm.analysis.repository.UserRepository;
import com.dreamteam.algorithm.analysis.web.service.user.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Value("${owner.default.password}")
    private String ownerPassword;

    public String loginUser(LoginDto details) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(details.getUsername(), details.getPassword()));
        userService.loadUserByUsername(details.getUsername());
        return jwtUtils.generateToken(details.getUsername());
    }

    public User registerUser(RegisterDto details) {
        throwIfUsernameExists(details.getUsername());
        return userRepository.save(new User(details));
    }


    private void throwIfUsernameExists(String username) {
        if (userRepository.existsUserByUsername(username)) {
            throw new UsernameExistsException(username);
        }
    }

    @PostConstruct
    public void init() {
        generateDefaultOwnerIfNeeded();
    }

    private void generateDefaultOwnerIfNeeded() {
        if (userRepository.existsUserByRole(Role.OWNER)) return;
        userRepository.save(User.builder()
                .username("admin")
                .password(passwordEncoder.encode(ownerPassword))
                .role(Role.OWNER)
                .build());
    }
}
