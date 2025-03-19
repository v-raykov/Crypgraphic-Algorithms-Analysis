package com.dreamteam.algorithm.analysis.domain.algorithm.impl.key.exchange;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;

public interface KeyExchangeAlgorithm extends Algorithm {
    byte[] generateKeyPair();
    byte[] deriveSharedSecret(byte[] publicKey, byte[] privateKey);
}
