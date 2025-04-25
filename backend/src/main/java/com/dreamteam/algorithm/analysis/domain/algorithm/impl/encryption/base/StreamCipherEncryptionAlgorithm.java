package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmType;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.parameter.EncryptionParameters;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.parameter.StreamCipherEncryptionParameters;

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
}
