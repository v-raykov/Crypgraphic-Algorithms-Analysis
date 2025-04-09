package com.dreamteam.algorithm.analysis.config.exception;

public class InvalidKeySizeException extends RuntimeException {
    public InvalidKeySizeException(String message, int keySize) {
        super(String.format("Invalid key size: %d, for algorithm %s", keySize, message));
    }
}
