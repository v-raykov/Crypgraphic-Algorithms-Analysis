package com.dreamteam.algorithm.analysis.domain.test.executor;

import com.dreamteam.algorithm.analysis.model.TestResult;
import com.dreamteam.algorithm.analysis.model.benchmark.SecurityBenchmark;

import java.util.Base64;

import static com.dreamteam.algorithm.analysis.domain.test.executor.SecurityAnalyzer.calculateEntropy;
import static com.dreamteam.algorithm.analysis.domain.test.executor.SecurityAnalyzer.calculateFrequencyScore;

public final class TestExecutorUtilities {
    static void executeSecurityBenchmarks(TestResult result) {
        String cipherText = result.getCipherText();
        result.setSecurity(new SecurityBenchmark(calculateEntropy(cipherText), calculateFrequencyScore(cipherText)));
    }

    static String encodeBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
}
