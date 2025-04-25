package com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.parameter;

import lombok.Getter;

@Getter
public class KeyDerivationParameters {
    private final byte[] salt;
    private final int keySize;

    protected KeyDerivationParameters(byte[] salt, int keySize) {
        this.salt = salt;
        this.keySize = keySize;
    }
}
