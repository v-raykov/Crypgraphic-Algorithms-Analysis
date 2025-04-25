package com.dreamteam.algorithm.analysis.web.service.algorithm;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base.KeyDerivationAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.parameter.KeyDerivationParameters;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.parameter.EncryptionParameters;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.model.test.KeyDerivationTest;
import com.dreamteam.algorithm.analysis.model.test.TestResult;
import com.dreamteam.algorithm.analysis.model.test.benchmark.SecurityBenchmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;

import static com.dreamteam.algorithm.analysis.web.service.algorithm.helper.SecurityAnalysisHelper.calculateEntropy;
import static com.dreamteam.algorithm.analysis.web.service.algorithm.helper.SecurityAnalysisHelper.calculateFrequencyScore;
import static com.dreamteam.algorithm.analysis.web.service.algorithm.helper.TestExecutionHelper.*;

@Service
@RequiredArgsConstructor
public class ExecutionService {
    public <P extends EncryptionParameters> TestResult testEncryption(EncryptionTest<P> test) {
        var result = new TestResult(test);
        var algorithm = test.getAlgorithm();
        executeEncryptionTest(algorithm, test.getPlaintext().getBytes(), test.getParameters(), result);
        executeSecurityBenchmarks(result);
        return result;
    }

    private <P extends EncryptionParameters> void executeEncryptionTest(EncryptionAlgorithm<P> algorithm, byte[] data, P parameters, TestResult result) {
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

    public <P extends KeyDerivationParameters> TestResult testKeyDerivation(KeyDerivationTest<P> test) {
        var result = new TestResult(test);
        var algorithm = test.getAlgorithm();
        executeKeyDerivationTest(algorithm, test.getPlaintext().getBytes(), test.getParameters(), result);
        executeSecurityBenchmarks(result);
        return result;
    }

    private <P extends KeyDerivationParameters> void executeKeyDerivationTest(KeyDerivationAlgorithm<P> algorithm, byte[] data, P parameters, TestResult result) {
        try {
            byte[] key = deriveKey(algorithm, data, parameters, result.getPerformance());
            result.setCipherText(encodeBase64(key));
            result.setTimestamp(LocalDateTime.now());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void executeSecurityBenchmarks(TestResult result) {
        var cipherText = result.getCipherText();
        result.setSecurity(new SecurityBenchmark(calculateEntropy(cipherText), calculateFrequencyScore(cipherText)));
    }

    private String encodeBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
}
