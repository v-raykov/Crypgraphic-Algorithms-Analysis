package com.dreamteam.algorithm.analysis.domain.algorithm.homomorphic;

public interface HomomorphicEncryptionAlgorithm {
    byte[] homomorphicOperation(byte[] encryptedData1, byte[] encryptedData2);
}
