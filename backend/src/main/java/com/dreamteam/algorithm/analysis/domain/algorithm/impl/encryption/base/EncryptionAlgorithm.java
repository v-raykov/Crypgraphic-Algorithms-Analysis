package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.parameter.EncryptionParameters;

public interface EncryptionAlgorithm<T extends EncryptionParameters> extends Algorithm {
    byte[] encrypt(byte[] data, T parameters) throws Exception;

    byte[] decrypt(byte[] data, T parameters) throws Exception;
}