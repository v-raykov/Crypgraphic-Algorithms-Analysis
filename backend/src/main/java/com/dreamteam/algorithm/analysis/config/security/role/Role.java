package com.dreamteam.algorithm.analysis.config.security.role;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    OWNER("ROLE_OWNER");

    private final String roleAsString;

    @Override
    public String toString() {
        return roleAsString;
    }
}
