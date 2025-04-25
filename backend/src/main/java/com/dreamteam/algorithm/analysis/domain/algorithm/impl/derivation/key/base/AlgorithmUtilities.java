package com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base;

import com.dreamteam.algorithm.analysis.config.exception.FaultyAlgorithmException;
import com.dreamteam.algorithm.analysis.config.exception.InvalidParameterException;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size.KeySizes;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size.MultipleFixedKeySizes;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size.SingleFixedKeySize;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size.VaryingKeySizes;

import java.security.SecureRandom;
import java.util.List;

public class AlgorithmUtilities {
    public static final SecureRandom secureRandom = new SecureRandom();

    public static int getRandomKeySize(Algorithm algorithm) {
        return switch (algorithm) {
            case MultipleFixedKeySizes a -> getRandomElement(a.getKeySizes());
            case SingleFixedKeySize a -> a.getKeySize();
            case VaryingKeySizes a -> a.getRandomKeySize();
            default -> throw new IllegalStateException("Unexpected value: " + algorithm);
        };
    }

    public static byte[] generateRandomKey(int keySize) {
        var key = new byte[keySize];
        secureRandom.nextBytes(key);
        return key;
    }

    public static void validateKeySize(Algorithm algorithm, int keySize) {
        if (!(algorithm instanceof KeySizes a)) {
            throw new FaultyAlgorithmException(algorithm.getName());
        }
        if (!a.isValidKeySize(keySize)) {
            throw new InvalidParameterException(algorithm.getName(), "keySize", keySize);
        }
    }

    private static int getRandomElement(List<Integer> list) {
        return list.get(secureRandom.nextInt(list.size()));
    }
}