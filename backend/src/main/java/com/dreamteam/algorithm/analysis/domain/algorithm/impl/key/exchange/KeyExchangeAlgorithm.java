package com.dreamteam.algorithm.analysis.domain.algorithm.impl.key.exchange;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.AlgorithmType;

import java.lang.reflect.Field;

public interface KeyExchangeAlgorithm extends Algorithm {
    byte[] generateKeyPair();
    byte[] deriveSharedSecret(byte[] publicKey, byte[] privateKey);

     default AlgorithmType getType() {
        return null;
    }

    @Override
     default Field[] getFields() {
        return new Field[0];
    }
}

