package com.dreamteam.algorithm.analysis.repository;

import com.dreamteam.algorithm.analysis.config.security.role.Role;
import com.dreamteam.algorithm.analysis.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsUserByRole(Role role);


    boolean existsUserByUsername(String username);

    Optional<User> findUserByUsername(String username);
}
