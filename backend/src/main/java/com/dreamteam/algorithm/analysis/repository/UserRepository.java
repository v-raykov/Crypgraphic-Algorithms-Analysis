package com.dreamteam.algorithm.analysis.repository;

import com.dreamteam.algorithm.analysis.config.security.role.Role;
import com.dreamteam.algorithm.analysis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsUserByRole(Role role);

    User getUserByUsername(String username);

    boolean existsUserByUsername(String username);
}
