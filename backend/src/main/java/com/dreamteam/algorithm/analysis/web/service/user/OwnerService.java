package com.dreamteam.algorithm.analysis.web.service.user;

import com.dreamteam.algorithm.analysis.config.exception.not.found.UserNotFoundException;
import com.dreamteam.algorithm.analysis.config.security.role.Role;
import com.dreamteam.algorithm.analysis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerService extends AbstractManagementService {
    private final UserRepository userRepository;
    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public void toggleUserAdmin(String id, boolean isAdmin) {
        var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setRole(isAdmin ? Role.ADMIN : Role.USER);
        userRepository.save(user);
    }

    @Override
    protected UserRepository getUserRepository() {
        return userRepository;
    }
}
