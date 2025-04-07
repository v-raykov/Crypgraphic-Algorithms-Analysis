package com.dreamteam.algorithm.analysis.web.controller;

import com.dreamteam.algorithm.analysis.model.test.Test;
import com.dreamteam.algorithm.analysis.model.test.TestResult;
import com.dreamteam.algorithm.analysis.web.service.algorithm.result.storage.ResultStorageService;
import com.dreamteam.algorithm.analysis.web.service.algorithm.TestingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AlgorithmController {
    private final TestingService testingService;
    private final ResultStorageService resultStorageService;
    @PostMapping
    public TestResult testAlgorithm(@RequestBody Test test) {
        return testingService.testAlgorithm(test, resultStorageService.getResultStorage());
    }
    @GetMapping
    public List<TestResult> getTestResults() {
        return resultStorageService.getResultStorage().getTestResults();
    }
}
