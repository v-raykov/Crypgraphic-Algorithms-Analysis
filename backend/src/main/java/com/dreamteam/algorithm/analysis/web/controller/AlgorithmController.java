package com.dreamteam.algorithm.analysis.web.controller;

import com.dreamteam.algorithm.analysis.model.User;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.dreamteam.algorithm.analysis.model.test.TestResult;
import com.dreamteam.algorithm.analysis.model.test.result.storage.ResultStorage;
import com.dreamteam.algorithm.analysis.model.test.result.storage.SessionResultStorage;
import com.dreamteam.algorithm.analysis.web.service.algorithm.TestingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AlgorithmController {
    private final TestingService testingService;
    private final SessionResultStorage sessionResultStorage;
    @PostMapping
    public TestResult testAlgorithm(@RequestBody Test test, @AuthenticationPrincipal User user) {
        return testingService.testAlgorithm(test, getResultStorage(user));
    }
    @GetMapping
    public List<TestResult> getTestResults(@AuthenticationPrincipal User user) {
        return getResultStorage(user).getTestResults();
    }

    private ResultStorage getResultStorage(@AuthenticationPrincipal User user) {
        return user == null ? sessionResultStorage : user;
    }
}
