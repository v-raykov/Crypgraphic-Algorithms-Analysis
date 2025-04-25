package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.parameter;

import lombok.Getter;

@Getter
public abstract class EncryptionParameters {
    private final byte[] encryptionKey;
    private final int keySize;

    public EncryptionParameters(byte[] encryptionKey) {
        this.encryptionKey = encryptionKey;
        keySize = encryptionKey.length;
    }
}
