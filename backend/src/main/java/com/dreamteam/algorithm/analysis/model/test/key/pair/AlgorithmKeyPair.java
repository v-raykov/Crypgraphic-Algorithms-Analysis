package com.dreamteam.algorithm.analysis.model.test.key.pair;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.KeyPair;
import java.security.spec.ECGenParameterSpec;

import static com.dreamteam.algorithm.analysis.model.test.key.pair.AlgorithmKeyPairUtils.getRawKeyPair;

@Getter
@AllArgsConstructor
public class AlgorithmKeyPair {
    private final byte[] publicKey;
    private final byte[] privateKey;

    public AlgorithmKeyPair(String algorithm, int keySize) {
        KeyPair rawKeyPair = getRawKeyPair(algorithm, keySize);
        privateKey = rawKeyPair.getPrivate().getEncoded();
        publicKey = rawKeyPair.getPublic().getEncoded();
    }

    public AlgorithmKeyPair(ECGenParameterSpec eCSpec) {
        KeyPair rawKeyPair = getRawKeyPair(eCSpec);
        privateKey = rawKeyPair.getPrivate().getEncoded();
        publicKey = rawKeyPair.getPublic().getEncoded();
    }
}
