package com.dreamteam.algorithm.analysis.web.service.algorithm.result.storage;

import com.dreamteam.algorithm.analysis.config.exception.not.found.TestResultNotFoundException;
import com.dreamteam.algorithm.analysis.model.test.TestResult;

import java.util.List;

public interface ResultStorage {
    void addTestResult(TestResult result);
    List<TestResult> getTestResults();
    default TestResult getTestResultById(String id) {
        return getTestResults().stream()
                .filter(result -> result.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TestResultNotFoundException(id));
    }
}
