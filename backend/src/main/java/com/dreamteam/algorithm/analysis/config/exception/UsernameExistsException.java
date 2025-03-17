package com.dreamteam.algorithm.analysis.config.exception;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException(String message) {
        super(String.format("Username already in use: %s", message));
    }
}
