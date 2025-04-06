package com.dreamteam.algorithm.analysis.repository;

import com.dreamteam.algorithm.analysis.config.security.role.Role;
import com.dreamteam.algorithm.analysis.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {
    boolean existsUserByRole(Role role);


    boolean existsUserByUsername(String username);

    Optional<User> findUserByUsername(String username);
}
