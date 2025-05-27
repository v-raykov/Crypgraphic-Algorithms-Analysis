package com.dreamteam.algorithm.analysis.domain.algorithm.impl.exchange.key;

import com.dreamteam.algorithm.analysis.config.jackson.FieldNames;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmType;
import com.dreamteam.algorithm.analysis.model.test.key.pair.AlgorithmKeyPair;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public abstract class KeyExchangeAlgorithm implements Algorithm {

    public AlgorithmKeyPair generateKeyPair(int keySize) {
        return new AlgorithmKeyPair(getAlgorithm(), keySize);
    }

    public abstract byte[] deriveSharedSecret(byte[] publicKeyBytes, byte[] privateKeyBytes) throws Exception;

    protected abstract String getAlgorithm();

    @Override
    public AlgorithmType getType() {
        return AlgorithmType.KEY_EXCHANGE;
    }

    @Override
    public Field[] getFields() {
        throw new UnsupportedOperationException("Method not supported");
    }

    @Override
    public List<String> getParameters() {
        return Collections.singletonList(FieldNames.KEY_SIZE.toString());
    }
}
