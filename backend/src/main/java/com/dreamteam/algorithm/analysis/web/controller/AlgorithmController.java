package com.dreamteam.algorithm.analysis.web.controller;

import com.dreamteam.algorithm.analysis.model.User;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.dreamteam.algorithm.analysis.model.test.TestResult;
import com.dreamteam.algorithm.analysis.web.service.algorithm.AlgorithmService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AlgorithmController {
    private final AlgorithmService algorithmService;
    @PostMapping
    public TestResult testAlgorithm(@RequestBody Test test, @AuthenticationPrincipal User user) {
        return algorithmService.testAlgorithm(test, Optional.ofNullable(user));
    }
}
