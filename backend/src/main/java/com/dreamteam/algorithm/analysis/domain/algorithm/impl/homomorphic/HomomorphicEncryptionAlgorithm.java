package com.dreamteam.algorithm.analysis.domain.algorithm.impl.homomorphic;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.AlgorithmType;

import java.lang.reflect.Field;

public interface HomomorphicEncryptionAlgorithm extends Algorithm {
    byte[] homomorphicOperation(byte[] encryptedData1, byte[] encryptedData2);

    @Override
     default AlgorithmType getType() {
        return null;
    }

    @Override
     default Field[] getFields() {
        return new Field[0];
    }
}
