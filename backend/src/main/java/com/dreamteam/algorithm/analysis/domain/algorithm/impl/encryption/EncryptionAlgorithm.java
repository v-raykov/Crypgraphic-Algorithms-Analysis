package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;

public interface EncryptionAlgorithm<T extends EncryptionParameters> extends Algorithm {
    byte[] encrypt(byte[] data, T parameters) throws Exception;
    byte[] decrypt(byte[] data, T parameters) throws Exception;
    boolean isValidKeySize(int keySize);
}
