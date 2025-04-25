package com.dreamteam.algorithm.analysis.config.jackson;

public enum FieldNames {
    ALGORITHM("algorithm"),
    ENCRYPTION_KEY("encryptionKey"),
    KEY_SIZE("keySize"),
    PARAMETERS("parameters"),
    IV("iv"),
    SALT("salt"),
    PLAINTEXT("plaintext"),
    COST_FACTOR("costFactor"),
    BLOCK_SIZE("blockSize"),
    PARALLELIZATION("parallelization"),
    ITERATIONS("iterations");

    private final String fieldName;

    FieldNames(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return fieldName;
    }
}
