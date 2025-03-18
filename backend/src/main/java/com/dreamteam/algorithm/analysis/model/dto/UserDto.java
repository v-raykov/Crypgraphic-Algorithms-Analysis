package com.dreamteam.algorithm.analysis.model.dto;

import com.dreamteam.algorithm.analysis.config.security.role.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    private String username;
    private String email;
    private Role role;
}
