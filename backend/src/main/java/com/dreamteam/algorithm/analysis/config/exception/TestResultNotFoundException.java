package com.dreamteam.algorithm.analysis.config.exception;

public class TestResultNotFoundException extends RuntimeException {
    public TestResultNotFoundException(String message) {
        super(String.format("Test result not found with id: %s", message));
    }
}
