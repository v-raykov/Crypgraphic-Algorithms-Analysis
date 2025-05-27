package com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size;

import static com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmUtilities.secureRandom;

public interface VaryingKeySizes extends KeySizes {
    int getMinKeySize();
    int getMaxKeySize();
    default int getRandomKeySize() {
        return secureRandom.nextInt(getMinKeySize(), getMaxKeySize());
    }

    @Override
    default boolean isValidKeySize(int keySize) {
        return keySize >= getMinKeySize() && keySize <= getMaxKeySize();
    }
}
