package com.dreamteam.algorithm.analysis.config.exception.unauthorized;

public class ForbiddenUserDeletionException extends RuntimeException {
    public ForbiddenUserDeletionException() {
        super("An admin can only be deleted by the owner");
    }
}
