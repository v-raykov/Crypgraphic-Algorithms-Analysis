package com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.AlgorithmType;

import java.lang.reflect.Field;

public interface KeyDerivationAlgorithm extends Algorithm {
    byte[] deriveKey(char[] password, byte[] salt, int iterations, int keyLength);

    @Override
    default AlgorithmType getType() {
        return AlgorithmType.KEY_DERIVATION;
    }

    @Override
    default Field[] getFields() {
        return new Field[0];
    }
}
