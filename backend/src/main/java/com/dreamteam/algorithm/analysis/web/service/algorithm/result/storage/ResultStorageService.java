package com.dreamteam.algorithm.analysis.web.service.algorithm.result.storage;

import com.dreamteam.algorithm.analysis.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultStorageService {
    private final SessionResultStorage sessionResultStorage;
    private final UserResultStorage userResultStorage;
    public ResultStorage getResultStorage() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return sessionResultStorage;
        } else {
            userResultStorage.setUser((User) authentication.getPrincipal());
            return userResultStorage;
        }
    }
}
