package com.dreamteam.algorithm.analysis.domain.algorithm.key.size;

import java.util.Random;

public interface VaryingKeySizes {
    int getMinKeySize();
    int getMaxKeySize();
    default int getRandomKeySize() {
        return new Random().nextInt(getMinKeySize(), getMaxKeySize());
    }
    default boolean isValidKey(byte[] key) {
        return key.length >= getMinKeySize() && key.length <= getMaxKeySize();
    }
}
