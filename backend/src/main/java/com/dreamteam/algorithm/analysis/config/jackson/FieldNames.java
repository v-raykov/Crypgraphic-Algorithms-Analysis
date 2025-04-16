package com.dreamteam.algorithm.analysis.config.jackson;

public enum FieldNames {
    ALGORITHM("algorithm"),
    ENCRYPTION_KEY("encryptionKey"),
    ENCRYPTION_KEY_SIZE("encryptionKeySize"),
    PARAMETERS("parameters"),
    IV("iv"),
    PLAINTEXT("plaintext");

    private final String fieldName;

    FieldNames(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return fieldName;
    }
}
