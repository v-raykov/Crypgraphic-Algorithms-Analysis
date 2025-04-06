package com.dreamteam.algorithm.analysis.model.test.result.storage;

import com.dreamteam.algorithm.analysis.model.test.TestResult;

import java.util.List;

public interface ResultStorage {
    default void addTestResult(TestResult result) {
        getTestResults().add(result);
    }
    List<TestResult> getTestResults();
}
