package com.dreamteam.algorithm.analysis.web.service.algorithm.result.storage;

import com.dreamteam.algorithm.analysis.model.test.TestResult;

import java.util.List;

public interface ResultStorage {
    TestResult addResult(TestResult result);
    List<TestResult> getResults();
    TestResult getResultById(String id);
}
