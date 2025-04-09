package com.dreamteam.algorithm.analysis.web.service.user;

import com.dreamteam.algorithm.analysis.model.User;
import com.dreamteam.algorithm.analysis.repository.UserRepository;

import java.util.List;

public abstract class AbstractManagementService {
    public List<User> getUsers() {
        return getUserRepository().findAll();
    }

    public abstract void deleteUser(String id);

    protected abstract UserRepository getUserRepository();
}
