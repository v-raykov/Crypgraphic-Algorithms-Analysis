package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.stream;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;

public class StreamCipherEncryptionAlgorithm implements EncryptionAlgorithm<StreamCipherEncryptionParameters> {
    @Override
    public byte[] encrypt(byte[] data, StreamCipherEncryptionParameters parameters) throws Exception {
        return new byte[0];
    }

    @Override
    public byte[] decrypt(byte[] data, StreamCipherEncryptionParameters parameters) throws Exception {
        return new byte[0];
    }

    @Override
    public boolean isValidKeySize(int keySize) {
        return false;
    }
}
