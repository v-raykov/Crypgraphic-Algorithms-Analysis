package com.dreamteam.algorithm.analysis.config.exception;

public class InvalidParameterException extends RuntimeException {
    public InvalidParameterException(String message, int keySize) {
        super(String.format("Invalid key size: %d, for algorithm %s", keySize, message));
    }
}
