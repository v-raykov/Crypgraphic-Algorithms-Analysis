package com.dreamteam.algorithm.analysis.web.service.user;

import com.dreamteam.algorithm.analysis.config.exception.ForbiddenUserDeletionException;
import com.dreamteam.algorithm.analysis.config.exception.UserNotFoundException;
import com.dreamteam.algorithm.analysis.config.security.role.Role;
import com.dreamteam.algorithm.analysis.model.User;
import com.dreamteam.algorithm.analysis.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends AbstractManagementService {
    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public void deleteUser(String id) {
        var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        throwIfUserIsAdmin(user);
        userRepository.delete(user);
    }

    private void throwIfUserIsAdmin(User user) {
        if (user.getRole() == Role.ADMIN || user.getRole() == Role.OWNER) {
            throw new ForbiddenUserDeletionException();
        }
    }
}
