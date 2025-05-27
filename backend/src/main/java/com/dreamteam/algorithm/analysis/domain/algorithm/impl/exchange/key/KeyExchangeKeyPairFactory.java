package com.dreamteam.algorithm.analysis.domain.algorithm.impl.exchange.key;

import com.dreamteam.algorithm.analysis.model.test.key.pair.AlgorithmKeyPair;

import static com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmUtilities.getRandomKeySize;
import static com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmUtilities.validateKeySize;

public class KeyExchangeKeyPairFactory {
    public static AlgorithmKeyPair generateKeyPair(KeyExchangeAlgorithm algorithm, int keySize) {
        if (keySize != 0) {
            validateKeySize(algorithm, keySize);
            return algorithm.generateKeyPair(keySize);
        }
        return algorithm.generateKeyPair(getRandomKeySize(algorithm));
    }
}
