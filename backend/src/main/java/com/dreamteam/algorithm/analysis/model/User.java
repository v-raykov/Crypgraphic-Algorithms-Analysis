package com.dreamteam.algorithm.analysis.model;

import com.dreamteam.algorithm.analysis.config.security.role.Role;
import com.dreamteam.algorithm.analysis.model.dto.RegisterDto;
import com.dreamteam.algorithm.analysis.model.test.TestResult;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class User implements UserDetails {
    @Id
    private String id;

    private String username;
    private String password;
    private String email;
    private Role role;

    private List<TestResult> testResults = new ArrayList<>();

    public User(RegisterDto details) {
        username = details.getUsername();
        password = details.getPassword();
        email = details.getEmail();
        role = Role.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
