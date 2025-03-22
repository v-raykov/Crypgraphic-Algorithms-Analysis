package com.dreamteam.algorithm.analysis.web.service.algorithm;

import com.dreamteam.algorithm.analysis.config.exception.FaultyAlgorithmException;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.option.RequiresIv;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTestResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class TestService {
    public EncryptionTestResult testEncryption(EncryptionAlgorithm algorithm, EncryptionTest test) {
        var result = new EncryptionTestResult(test);
        setIvIfRequired(algorithm, test);
        String encrypted = performEncryptionTest(algorithm, test.getPlaintext(), test.getEncryptionKey());
        result.setResult(encrypted);
        result.setTimestamp(LocalDateTime.now());
        return result;
    }

    private String performEncryptionTest(EncryptionAlgorithm algorithm, String plaintext, byte[] key) {
        try {
            byte[] encrypted = algorithm.encrypt(plaintext.getBytes(), key);
            byte[] decrypted = algorithm.decrypt(encrypted, key);
            validateResult(decrypted, plaintext, algorithm.getName());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setIvIfRequired(EncryptionAlgorithm algorithm, EncryptionTest test) {
        if (algorithm instanceof RequiresIv requiresIv) {
            requiresIv.setIv(test.getIv());
        }
    }

    private void validateResult(byte[] decrypted, String plaintext, String algorithmName) {
        if (!new String(decrypted, StandardCharsets.UTF_8).equals(plaintext)) {
            throw new FaultyAlgorithmException(algorithmName);
        }
    }
}
