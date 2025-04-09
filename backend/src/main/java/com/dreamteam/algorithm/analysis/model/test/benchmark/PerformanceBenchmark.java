package com.dreamteam.algorithm.analysis.model.test.benchmark;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PerformanceBenchmark {
    private long cipherTime;
    private long decipherTime;
    private long cipherMemory;
    private long decipherMemory;
}
