package com.dreamteam.algorithm.analysis.web.service.algorithm.result.storage;

import com.dreamteam.algorithm.analysis.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
@Configuration
public class ResultStorageConfig {
    private final SessionResultStorage sessionResultStorage;
    private final UserResultStorage userResultStorage;

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ResultStorage resultStorage() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return sessionResultStorage;
        } else {
            userResultStorage.setUser((User) authentication.getPrincipal());
            return userResultStorage;
        }
    }
}
