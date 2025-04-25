package com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.parameter;

import lombok.Getter;

@Getter
public class CountBasedKeyDerivationParameters extends KeyDerivationParameters {
    private final int iterations;

    protected CountBasedKeyDerivationParameters(byte[] salt, int keySize, int iterations) {
        super(salt, keySize);
        this.iterations = iterations;
    }
}
