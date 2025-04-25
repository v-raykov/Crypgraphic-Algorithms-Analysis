package com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.parameter;

import lombok.Getter;

@Getter
public class ResourceBasedKeyDerivationParameters extends KeyDerivationParameters {
    private final int costFactor;
    private final int blockSize;
    private final int parallelization;

    protected ResourceBasedKeyDerivationParameters(byte[] salt, int keySize, int costFactor, int blockSize, int parallelization) {
        super(salt, keySize);
        this.costFactor = costFactor;
        this.blockSize = blockSize;
        this.parallelization = parallelization;
    }
}
