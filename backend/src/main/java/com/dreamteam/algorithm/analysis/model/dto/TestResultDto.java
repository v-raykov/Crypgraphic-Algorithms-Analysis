package com.dreamteam.algorithm.analysis.model.dto;

import com.dreamteam.algorithm.analysis.model.test.Test;
import com.dreamteam.algorithm.analysis.model.test.benchmark.PerformanceBenchmark;
import com.dreamteam.algorithm.analysis.model.test.benchmark.SecurityBenchmark;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
public class TestResultDto {
    private String id;
    private LocalDateTime timestamp;
    private Test test;
    private String cipherText;
    private PerformanceBenchmark performance;
    private SecurityBenchmark security;

}
