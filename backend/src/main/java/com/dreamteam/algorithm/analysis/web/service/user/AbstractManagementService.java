package com.dreamteam.algorithm.analysis.web.service.user;

import com.dreamteam.algorithm.analysis.model.User;
import com.dreamteam.algorithm.analysis.repository.UserRepository;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public abstract class AbstractManagementService {
    private UserRepository userRepository;

    public AbstractManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public abstract void deleteUser(String id);

}
