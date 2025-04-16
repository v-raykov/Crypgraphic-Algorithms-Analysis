package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.stream;

import com.dreamteam.algorithm.analysis.domain.algorithm.AlgorithmType;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionParameters;

import java.lang.reflect.Field;

public class StreamCipherEncryptionAlgorithm implements EncryptionAlgorithm<StreamCipherEncryptionParameters> {
    @Override
    public AlgorithmType getType() {
        return AlgorithmType.STREAM_CIPHER_ENCRYPTION;
    }

    @Override
    public Field[] getFields() {
        return EncryptionParameters.class.getDeclaredFields();
    }

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
