package com.dreamteam.algorithm.analysis.web.service.user;

import com.dreamteam.algorithm.analysis.config.exception.not.found.UserNotFoundException;
import com.dreamteam.algorithm.analysis.config.exception.unauthorized.ForbiddenUserDeletionException;
import com.dreamteam.algorithm.analysis.config.security.role.Role;
import com.dreamteam.algorithm.analysis.model.User;
import com.dreamteam.algorithm.analysis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService extends AbstractManagementService {
    private final UserRepository userRepository;
    @Override
    public void deleteUser(String id) {
        var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        throwIfUserIsAdmin(user);
        userRepository.delete(user);
    }

    private void throwIfUserIsAdmin(User user) {
        if (user.getRole() == Role.ADMIN || user.getRole() == Role.OWNER) {
            throw new ForbiddenUserDeletionException();
        }
    }

    @Override
    protected UserRepository getUserRepository() {
        return userRepository;
    }
}
