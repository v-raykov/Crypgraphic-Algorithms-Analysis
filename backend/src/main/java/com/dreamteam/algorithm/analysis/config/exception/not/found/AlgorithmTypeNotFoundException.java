package com.dreamteam.algorithm.analysis.config.exception.not.found;

public class AlgorithmTypeNotFoundException extends RuntimeException {
    public AlgorithmTypeNotFoundException(String message) {
        super(String.format("Algorithm type not found: %s", message));
    }
}
