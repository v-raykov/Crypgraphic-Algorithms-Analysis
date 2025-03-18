package com.dreamteam.algorithm.analysis.web.service.user;

import com.dreamteam.algorithm.analysis.config.exception.UserNotFoundException;
import com.dreamteam.algorithm.analysis.config.security.role.Role;
import com.dreamteam.algorithm.analysis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OwnerService extends AbstractManagementService {
    private final UserRepository userRepository;

    public OwnerService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public void toggleUserAdmin(UUID id, boolean isAdmin) {
        var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        user.setRole(isAdmin ? Role.ADMIN : Role.USER);
        userRepository.save(user);
    }
}
