package com.dreamteam.algorithm.analysis.model.benchmark;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PerformanceBenchmark {
    private long cipherTime;
    private long cipherMemory;
    private long decipherTime;
    private long decipherMemory;
}
