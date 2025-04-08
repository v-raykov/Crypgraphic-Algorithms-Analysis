package com.dreamteam.algorithm.analysis.config.exception.not.found;

public class AlgorithmNotFoundException extends RuntimeException {
    public AlgorithmNotFoundException(String message) {
        super(String.format("Algorithm with name: %s does not exist", message));
    }
}
