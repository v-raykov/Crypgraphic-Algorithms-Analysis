package com.dreamteam.algorithm.analysis.web.service.algorithm;

import com.dreamteam.algorithm.analysis.config.exception.AlgorithmDoesNotExistsException;
import com.dreamteam.algorithm.analysis.config.exception.FaultyAlgorithmException;
import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.MultipleFixedKeySizes;
import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.SingleFixedKeySize;
import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.VaryingKeySizes;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.model.test.TestResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AlgorithmService {
    private final Map<String, Algorithm> algorithms;
    private final SecureRandom secureRandom = new SecureRandom();
    private TestResult testEncryption(EncryptionTest test) throws Exception {

        var algorithm = (EncryptionAlgorithm) findAlgorithm(test.getAlgorithmName());
        var key = getKeyFromTestOrDefault(test, algorithm);
        var plaintext = test.getPlaintext();

        byte[] encrypted = algorithm.encrypt(plaintext.getBytes(), key);
        byte[] decrypted = algorithm.decrypt(encrypted, key);

        validateResult(decrypted, plaintext, algorithm.getName());
        return getResult(test, encrypted);
    }

    private TestResult getResult(EncryptionTest test, byte[] encrypted) {
        var result = new TestResult(test);
        result.setTimestamp(LocalDateTime.now());
        result.setResult(Base64.getEncoder().encodeToString(encrypted));
        return result;
    }

    private void validateResult(byte[] decrypted, String plaintext, String algorithmName) {
        if (!new String(decrypted, StandardCharsets.UTF_8).equals(plaintext)) {
            throw new FaultyAlgorithmException(algorithmName);
        }
    }

    private byte[] getKeyFromTestOrDefault(EncryptionTest test, EncryptionAlgorithm algorithm) {
        var key = test.getKey().getBytes(StandardCharsets.UTF_8);
        var keySize = test.getKeySize();
        if (key.length == 0) {
            if (keySize == 0) {
                return getKey(getKeySize(algorithm));
            }
            key = getKey(keySize);
            if (algorithm.isValidKeySize(keySize)) {
                return key;
            }
            throw new IllegalArgumentException(keySize + " is not a valid key");
        }
        if (algorithm.isValidKeySize(keySize)) {
            return key;
        }
        throw new IllegalArgumentException(keySize + " is not a valid key");
    }

    private Algorithm findAlgorithm(String algorithmName) {
        var algorithm = algorithms.get(algorithmName);
        if (algorithm == null) {
            throw new AlgorithmDoesNotExistsException(algorithmName);
        }
        return algorithm;
    }

    private byte[] getKey(int keySize) {
        var key = new byte[keySize];
        secureRandom.nextBytes(key);
        return key;
    }

    private int getKeySize(Algorithm algorithm) {
        return switch (algorithm) {
            case MultipleFixedKeySizes a -> a.getKeySizes().getFirst();
            case SingleFixedKeySize a -> a.getKeySize();
            case VaryingKeySizes a -> a.getRandomKeySize();
            default -> throw new IllegalStateException("Unexpected value: " + algorithm);
        };
    }

}
