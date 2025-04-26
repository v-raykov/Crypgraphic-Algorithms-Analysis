package com.dreamteam.algorithm.analysis.config.exception;

public class InvalidParameterException extends RuntimeException {
    public InvalidParameterException(String algorithm, String parameter, int keySize) {
        super(String.format("Invalid size: %d for parameter: %s in algorithm: %s", keySize, parameter, algorithm));
    }

    public InvalidParameterException(String algorithm, Exception cause) {
        super(String.format("Invalid parameter in algorithm: %s", algorithm), cause);
    }
}
