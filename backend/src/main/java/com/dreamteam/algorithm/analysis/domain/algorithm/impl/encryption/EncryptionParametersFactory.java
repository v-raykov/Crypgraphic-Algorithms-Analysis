package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption;

import com.dreamteam.algorithm.analysis.config.exception.InvalidParameterException;
import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.block.BlockCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.block.BlockCipherEncryptionParameters;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.stream.StreamCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.stream.StreamCipherEncryptionParameters;
import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.MultipleFixedKeySizes;
import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.SingleFixedKeySize;
import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.VaryingKeySizes;

import java.util.List;

import static com.dreamteam.algorithm.analysis.config.GlobalStaticConstants.secureRandom;

public class EncryptionParametersFactory {
    public static BlockCipherEncryptionParameters createBlockCipherEncryptionParameters(BlockCipherEncryptionAlgorithm algorithm, byte[] key, int keySize, byte[] iv) {
        key = processKey(algorithm, key, keySize);
        iv = processIv(algorithm, iv);
        return new BlockCipherEncryptionParameters(key, iv);
    }

    public static StreamCipherEncryptionParameters createStreamCipherEncryptionParameters(StreamCipherEncryptionAlgorithm algorithm, byte[] key, int keySize) {
        key = processKey(algorithm, key, keySize);
        return new StreamCipherEncryptionParameters(key);
    }

    private static byte[] processKey(EncryptionAlgorithm<?> algorithm, byte[] key, int keySize) {
        if (key != null) {
            validateKeySize(algorithm, key.length);
            return key;
        }
        return getDefaultEncryptionKey(algorithm, keySize);
    }

    private static byte[] processIv(BlockCipherEncryptionAlgorithm algorithm, byte[] iv) {
        if (iv != null) {
            validateIvSize(algorithm, iv);
            return iv;
        }
        return algorithm.generateRandomIv();
    }

    private static byte[] getDefaultEncryptionKey(EncryptionAlgorithm<?> algorithm, int keySize) {
        if (keySize != 0) {
            validateKeySize(algorithm, keySize);
            return generateRandomKey(keySize);
        }
        return generateRandomKey(getKeySize(algorithm));
    }

    private static byte[] generateRandomKey(int keySize) {
        var key = new byte[keySize];
        secureRandom.nextBytes(key);
        return key;
    }

    private static int getKeySize(Algorithm algorithm) {
        return switch (algorithm) {
            case MultipleFixedKeySizes a -> getRandomElement(a.getKeySizes());
            case SingleFixedKeySize a -> a.getKeySize();
            case VaryingKeySizes a -> a.getRandomKeySize();
            default -> throw new IllegalStateException("Unexpected value: " + algorithm);
        };
    }

    private static int getRandomElement(List<Integer> list) {
        return list.get(secureRandom.nextInt(list.size()));
    }

    private static void validateKeySize(EncryptionAlgorithm<?> algorithm, int keySize) {
        if (!algorithm.isValidKeySize(keySize)) {
            throw new InvalidParameterException(algorithm.getName(), keySize);
        }
    }

    private static void validateIvSize(BlockCipherEncryptionAlgorithm algorithm, byte[] iv) {
        if (iv.length != algorithm.getIvSize()) {
            throw new InvalidParameterException(algorithm.getName(), iv.length);
        }
    }
}
