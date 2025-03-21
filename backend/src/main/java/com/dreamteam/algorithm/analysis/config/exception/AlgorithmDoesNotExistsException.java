package com.dreamteam.algorithm.analysis.config.exception;

public class AlgorithmDoesNotExistsException extends RuntimeException {
    public AlgorithmDoesNotExistsException(String message) {
        super(String.format("Algorithm with name: %s does not exist", message));
    }
}
