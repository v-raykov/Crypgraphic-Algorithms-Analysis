package com.dreamteam.algorithm.analysis.domain.algorithm.impl.homomorphic;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;

public interface HomomorphicEncryptionAlgorithm extends Algorithm {
    byte[] homomorphicOperation(byte[] encryptedData1, byte[] encryptedData2);
}
