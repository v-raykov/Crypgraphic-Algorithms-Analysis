package com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size;

public interface SingleFixedKeySize extends KeySizes {
    int getKeySize();

    @Override
    default boolean isValidKeySize(int keySize) {
        return getKeySize() == keySize;
    }
}
