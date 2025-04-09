package com.dreamteam.algorithm.analysis.web.service.algorithm;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.model.test.TestResult;
import com.dreamteam.algorithm.analysis.model.test.benchmark.SecurityBenchmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;

import static com.dreamteam.algorithm.analysis.web.service.algorithm.helper.SecurityAnalysisHelper.*;
import static com.dreamteam.algorithm.analysis.web.service.algorithm.helper.TestExecutionHelper.*;

@Service
@RequiredArgsConstructor
public class ExecutionService {
    public TestResult testEncryption(EncryptionTest test) {
        var result = new TestResult(test);
        var algorithm = test.getAlgorithm();
        applyInitializationVectorIfNeeded(algorithm, test.getIv());
        executeEncryptionTest(algorithm, test.getPlaintext(), test.getEncryptionKey(), result);
        executeSecurityBenchmarks(result);
        return result;
    }

    private void executeSecurityBenchmarks(TestResult result) {
        var cipherText = result.getCipherText();
        result.setSecurity(new SecurityBenchmark(calculateEntropy(cipherText), calculateFrequencyScore(cipherText)));
    }

    private void executeEncryptionTest(EncryptionAlgorithm algorithm, String plaintext, byte[] key, TestResult result) {
        try {
            byte[] encrypted = encryptData(algorithm, plaintext, key, result.getPerformance());
            byte[] decrypted = decryptData(algorithm, encrypted, key, result.getPerformance());
            validateDecryption(decrypted, plaintext, algorithm.getName());
            result.setCipherText(encodeBase64(encrypted));
            result.setTimestamp(LocalDateTime.now());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String encodeBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

}
