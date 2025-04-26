package com.dreamteam.algorithm.analysis.config.exception;

public class FaultyAlgorithmException extends RuntimeException {
    public FaultyAlgorithmException(String message) {
        super(String.format("Algorithm, with name: %s, is implemented incorrectly", message));
    }

    public FaultyAlgorithmException(String message, Exception cause) {
        super(String.format("Algorithm, with name: %s, is implemented incorrectly", message), cause);
    }
}
