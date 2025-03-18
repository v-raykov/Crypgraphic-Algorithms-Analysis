package com.dreamteam.algorithm.analysis.config.exception;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {
        super("Password is incorrect");
    }
}
