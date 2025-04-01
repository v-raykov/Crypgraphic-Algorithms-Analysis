package com.dreamteam.algorithm.analysis.domain.algorithm;

import com.dreamteam.algorithm.analysis.config.jackson.AlgorithmSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = AlgorithmSerializer.class)
public interface Algorithm {
    default String getName() {
        return getClass().getSimpleName();
    }
}
