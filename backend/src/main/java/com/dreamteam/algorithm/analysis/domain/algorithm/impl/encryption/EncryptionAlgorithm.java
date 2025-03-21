package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;

public interface EncryptionAlgorithm extends Algorithm {
    byte[] encrypt(byte[] data, byte[] key) throws Exception;
    byte[] decrypt(byte[] data, byte[] key) throws Exception;
    boolean validateKey(byte[] key);
}
