package com.dreamteam.algorithm.analysis.web.service.algorithm;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTestResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;

import static com.dreamteam.algorithm.analysis.web.service.algorithm.helper.TestExecutionHelper.*;

@Service
@RequiredArgsConstructor
public class TestService {
    public EncryptionTestResult testEncryption(EncryptionAlgorithm algorithm, EncryptionTest test) {
        var result = new EncryptionTestResult(test);
        applyInitializationVectorIfNeeded(algorithm, test);
        executeEncryptionTest(algorithm, test.getPlaintext(), test.getEncryptionKey(), result);
        return result;
    }

    private void executeEncryptionTest(EncryptionAlgorithm algorithm, String plaintext, byte[] key, EncryptionTestResult result) {
        try {
            byte[] encrypted = encryptData(algorithm, plaintext, key, result);
            byte[] decrypted = decryptData(algorithm, encrypted, key, result);
            validateDecryption(decrypted, plaintext, algorithm.getName());

            result.setResult(encodeBase64(encrypted));
            result.setTimestamp(LocalDateTime.now());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String encodeBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }


}
