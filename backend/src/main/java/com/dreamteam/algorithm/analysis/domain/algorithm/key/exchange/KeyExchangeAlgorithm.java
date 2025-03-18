package com.dreamteam.algorithm.analysis.domain.algorithm.key.exchange;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;

public interface KeyExchangeAlgorithm extends Algorithm {
    byte[] generateKeyPair();
    byte[] deriveSharedSecret(byte[] publicKey, byte[] privateKey);
}
