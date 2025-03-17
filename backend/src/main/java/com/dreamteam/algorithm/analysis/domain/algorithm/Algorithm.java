package com.dreamteam.algorithm.analysis.domain.algorithm;

public interface Algorithm {
    default String getName() {
        return getClass().getSimpleName();
    }
}
