package com.dreamteam.algorithm.analysis.model.test.key.pair;

import com.dreamteam.algorithm.analysis.config.exception.FaultyAlgorithmException;

import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;

public final class AlgorithmKeyPairUtils {
    static KeyPair getRawKeyPair(String algorithm, int keySize) {
        try {
            return generateKeyPair(KeyPairGenerator.getInstance(algorithm), keySize * 8);
        } catch (NoSuchAlgorithmException e) {
            throw new FaultyAlgorithmException(algorithm, e);
        }
    }

    static KeyPair getRawKeyPair(ECGenParameterSpec eCSpec) {
        try {
            return generateKeyPair(KeyPairGenerator.getInstance("EC", "BC"), eCSpec);
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new FaultyAlgorithmException("EC", e);
        }
    }

    private static KeyPair generateKeyPair(KeyPairGenerator keyGen, AlgorithmParameterSpec spec) {
        try {
            keyGen.initialize(spec);
        } catch (InvalidAlgorithmParameterException e) {
            throw new FaultyAlgorithmException("EC", e);
        }
        return keyGen.generateKeyPair();
    }

    private static KeyPair generateKeyPair(KeyPairGenerator keyGen, int keySize) {
        keyGen.initialize(keySize);
        return keyGen.generateKeyPair();
    }
}
