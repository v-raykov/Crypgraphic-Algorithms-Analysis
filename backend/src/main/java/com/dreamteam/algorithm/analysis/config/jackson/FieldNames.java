package com.dreamteam.algorithm.analysis.config.jackson;

public enum FieldNames {
    ALGORITHM("algorithm"),
    ENCRYPTION_KEY("encryptionKey"),
    KEY_SIZE("keySize"),
    PARAMETERS("parameters"),
    IV("iv"),
    SALT("salt"),
    DATA("data"),
    COST_FACTOR("costFactor"),
    BLOCK_SIZE("blockSize"),
    PARALLELIZATION("parallelization"),
    ITERATIONS("iterations"),
    KEY_PAIR("keyPair"),
    PRIVATE_KEY("privateKey"),
    PUBLIC_KEY("publicKey");

    private final String fieldName;

    FieldNames(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return fieldName;
    }
}
