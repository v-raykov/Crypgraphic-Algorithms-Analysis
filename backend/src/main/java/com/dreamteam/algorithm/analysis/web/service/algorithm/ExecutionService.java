package com.dreamteam.algorithm.analysis.web.service.algorithm;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionParameters;
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
    public <T extends EncryptionParameters> TestResult testEncryption(EncryptionTest<T> test) {
        var result = new TestResult(test);
        var algorithm = test.getAlgorithm();
        executeEncryptionTest(algorithm, test.getPlaintext().getBytes(), test.getParameters(), result);
        executeSecurityBenchmarks(result);
        return result;
    }

    private void executeSecurityBenchmarks(TestResult result) {
        var cipherText = result.getCipherText();
        result.setSecurity(new SecurityBenchmark(calculateEntropy(cipherText), calculateFrequencyScore(cipherText)));
    }

    private <T extends EncryptionParameters> void executeEncryptionTest(EncryptionAlgorithm<T> algorithm, byte[] data, T parameters, TestResult result) {
        try {
            byte[] encrypted = encryptData(algorithm, data, parameters, result.getPerformance());
            byte[] decrypted = decryptData(algorithm, encrypted, parameters, result.getPerformance());
            validateDecryption(decrypted, decrypted, algorithm.getName());
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
