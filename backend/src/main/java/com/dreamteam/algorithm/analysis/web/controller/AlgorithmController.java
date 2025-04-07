package com.dreamteam.algorithm.analysis.web.controller;

import com.dreamteam.algorithm.analysis.model.test.Test;
import com.dreamteam.algorithm.analysis.model.test.TestResult;
import com.dreamteam.algorithm.analysis.web.service.algorithm.TestingService;
import com.dreamteam.algorithm.analysis.web.service.algorithm.result.storage.ResultStorage;
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
    private final ResultStorage resultStorage;
    @PostMapping
    public TestResult testAlgorithm(@RequestBody Test test) {
        return testingService.testAlgorithm(test, resultStorage);
    }
    @GetMapping
    public List<TestResult> getTestResults() {
        return resultStorage.getTestResults();
    }
}
