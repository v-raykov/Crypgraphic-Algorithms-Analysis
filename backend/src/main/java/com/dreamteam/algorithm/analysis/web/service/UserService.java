package com.dreamteam.algorithm.analysis.web.service;

import com.dreamteam.algorithm.analysis.config.exception.UsernameExistsException;
import com.dreamteam.algorithm.analysis.config.security.role.Role;
import com.dreamteam.algorithm.analysis.model.User;
import com.dreamteam.algorithm.analysis.model.dto.RegisterDto;
import com.dreamteam.algorithm.analysis.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${owner.default.password}")
    private String ownerPassword;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUsername(username);
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
