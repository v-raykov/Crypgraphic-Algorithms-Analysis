package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption;

import lombok.Getter;

@Getter
public abstract class EncryptionParameters {
    private final byte[] encryptionKey;
    private final int encryptionKeySize;

    public EncryptionParameters(byte[] encryptionKey) {
        this.encryptionKey = encryptionKey;
        encryptionKeySize = encryptionKey.length;
    }
}
