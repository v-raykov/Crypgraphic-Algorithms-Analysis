package com.dreamteam.algorithm.analysis.domain.algorithm.impl.digital.signature.parameter;

import com.dreamteam.algorithm.analysis.config.exception.FaultyAlgorithmException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

@Getter
@AllArgsConstructor
public class DigitalSignatureKeyPair {
    private final byte[] publicKey;
    private final byte[] privateKey;

    public DigitalSignatureKeyPair(String algorithm, int keySize) {
        KeyPairGenerator keyGen = getKeyGen(algorithm);
        keyGen.initialize(keySize);
        KeyPair rawKeyPair = keyGen.generateKeyPair();
        privateKey = rawKeyPair.getPrivate().getEncoded();
        publicKey = rawKeyPair.getPublic().getEncoded();
    }

    private KeyPairGenerator getKeyGen(String algorithm) {
        try {
            return KeyPairGenerator.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new FaultyAlgorithmException(algorithm, e);
        }
    }
}
