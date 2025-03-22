package com.dreamteam.algorithm.analysis.model.test;

import java.time.LocalDateTime;

public interface TestResult {
    String getResult();
    void setResult(String encrypted);
    LocalDateTime getTimestamp();
    void setTimestamp(LocalDateTime timestamp);
}
