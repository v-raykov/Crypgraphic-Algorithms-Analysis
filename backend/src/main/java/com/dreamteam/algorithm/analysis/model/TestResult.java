package com.dreamteam.algorithm.analysis.model;

import com.dreamteam.algorithm.analysis.model.test.Test;
import com.dreamteam.algorithm.analysis.model.benchmark.PerformanceBenchmark;
import com.dreamteam.algorithm.analysis.model.benchmark.SecurityBenchmark;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document
@NoArgsConstructor
public class TestResult {
    @Id
    private String id;
    private String ownerId;

    private Test test;
    private String cipherText;
    private PerformanceBenchmark performance;
    private SecurityBenchmark security;
    private LocalDateTime timestamp;

    public TestResult(Test test) {
        this.test = test;
        performance = new PerformanceBenchmark();
    }
}
