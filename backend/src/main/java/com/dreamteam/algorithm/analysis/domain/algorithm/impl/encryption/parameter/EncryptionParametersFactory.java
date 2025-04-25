package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.parameter;

import com.dreamteam.algorithm.analysis.config.exception.InvalidParameterException;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.BlockCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.StreamCipherEncryptionAlgorithm;

import static com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base.AlgorithmUtilities.*;

public class EncryptionParametersFactory {
    public static BlockCipherEncryptionParameters createBlockCipherEncryptionParameters(BlockCipherEncryptionAlgorithm algorithm, byte[] key, int keySize, byte[] iv) {
        return new BlockCipherEncryptionParameters(
                processKey(algorithm, key, keySize),
                processIv(algorithm, iv)
        );
    }

    public static StreamCipherEncryptionParameters createStreamCipherEncryptionParameters(StreamCipherEncryptionAlgorithm algorithm, byte[] key, int keySize) {
        return new StreamCipherEncryptionParameters(processKey(algorithm, key, keySize));
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
        return generateRandomKey(getRandomKeySize(algorithm));
    }

    private static void validateIvSize(BlockCipherEncryptionAlgorithm algorithm, byte[] iv) {
        if (iv.length != algorithm.getIvSize()) {
            throw new InvalidParameterException(algorithm.getName(), "iv", iv.length);
        }
    }
}
