package com.dreamteam.algorithm.analysis.model.dto;

import com.dreamteam.algorithm.analysis.model.test.Test;
import com.dreamteam.algorithm.analysis.model.test.TestResult;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TestResultDto {
    private final String id;
    private final Test test;
    private final String cipherText;
    private final long cipherTime;
    private final long decipherTime;
    private final long cipherMemory;
    private final long decipherMemory;
    private final double entropy;
    private final double frequencyScore;
    private final LocalDateTime timestamp;

    public TestResultDto(TestResult testResult) {
        id = testResult.getId();
        test = testResult.getTest();
        cipherText = testResult.getCipherText();
        cipherTime = testResult.getCipherTime();
        decipherTime = testResult.getDecipherTime();
        cipherMemory = testResult.getCipherMemory();
        decipherMemory = testResult.getDecipherMemory();
        entropy = testResult.getEntropy();
        frequencyScore = testResult.getFrequencyScore();
        timestamp = testResult.getTimestamp();
    }
}
