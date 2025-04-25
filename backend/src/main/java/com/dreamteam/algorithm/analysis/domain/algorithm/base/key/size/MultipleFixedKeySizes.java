package com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size;

import java.util.List;

public interface MultipleFixedKeySizes extends KeySizes{
    List<Integer> getKeySizes();
    default boolean isValidKeySize(int keySize) {
        return getKeySizes().contains(keySize);
    }
}
