package com.dreamteam.algorithm.analysis.web.service.algorithm;

import com.dreamteam.algorithm.analysis.config.exception.InvalidParameterException;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash.HashAlgorithm;
import com.dreamteam.algorithm.analysis.model.test.*;
import com.dreamteam.algorithm.analysis.model.test.key.pair.AlgorithmKeyPair;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base.KeyDerivationAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.parameter.KeyDerivationParameters;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.digital.signature.DigitalSignatureAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.parameter.EncryptionParameters;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.exchange.key.KeyExchangeAlgorithm;
import com.dreamteam.algorithm.analysis.model.TestResult;
import com.dreamteam.algorithm.analysis.model.benchmark.SecurityBenchmark;
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
        executeEncryptionTest(algorithm, test.getData().getBytes(), test.getParameters(), result);
        executeSecurityBenchmarks(result);
        return result;
    }

    public <P extends KeyDerivationParameters> TestResult testKeyDerivation(KeyDerivationTest<P> test) {
        var result = new TestResult(test);
        var algorithm = test.getAlgorithm();
        executeKeyDerivationTest(algorithm, test.getData().getBytes(), test.getParameters(), result);
        executeSecurityBenchmarks(result);
        return result;
    }

    public TestResult testDigitalSignature(DigitalSignatureTest test) {
        var result = new TestResult(test);
        var algorithm = test.getAlgorithm();
        executeDigitalSignatureTest(algorithm, test.getData().getBytes(), test.getAlgorithmKeyPair(), result);
        executeSecurityBenchmarks(result);
        return result;
    }

    public TestResult testKeyExchange(KeyExchangeTest test) {
        var result = new TestResult(test);
        var algorithm = test.getAlgorithm();
        executeKeyExchangeTest(algorithm, test.getKeyPair(), result);
        executeSecurityBenchmarks(result);
        return result;
    }

    public TestResult testHash(HashTest test) {
        var result = new TestResult(test);
        var algorithm = test.getAlgorithm();
        executeHashTest(algorithm, test.getData().getBytes(), result);
        executeSecurityBenchmarks(result);
        return result;
    }

    private void executeKeyExchangeTest(KeyExchangeAlgorithm algorithm, AlgorithmKeyPair algorithmKeyPair, TestResult result) {
        try {
            byte[] shared = deriveSharedSecret(algorithm, algorithmKeyPair, result.getPerformance());
            result.setCipherText(encodeBase64(shared));
            result.setTimestamp(LocalDateTime.now());
        } catch (Exception e) {
            throw new InvalidParameterException(algorithm.getName(), e);
        }
    }

    private void executeDigitalSignatureTest(DigitalSignatureAlgorithm algorithm, byte[] bytes, AlgorithmKeyPair algorithmKeyPair, TestResult result) {
        try {
            byte[] signature = signData(algorithm, bytes, algorithmKeyPair, result.getPerformance());
            validateSignature(algorithm, bytes, signature, algorithmKeyPair, result.getPerformance());
            result.setCipherText(encodeBase64(signature));
            result.setTimestamp(LocalDateTime.now());
        } catch (Exception e) {
            throw new InvalidParameterException(algorithm.getName(), e);
        }
    }

    private <P extends EncryptionParameters> void executeEncryptionTest(EncryptionAlgorithm<P> algorithm, byte[] data, P parameters, TestResult result) {
        try {
            byte[] encrypted = encryptData(algorithm, data, parameters, result.getPerformance());
            byte[] decrypted = decryptData(algorithm, encrypted, parameters, result.getPerformance());
            validateDecryption(decrypted, decrypted, algorithm.getName());
            result.setCipherText(encodeBase64(encrypted));
            result.setTimestamp(LocalDateTime.now());
        } catch (Exception e) {
            throw new InvalidParameterException(algorithm.getName(), e);
        }
    }

    private <P extends KeyDerivationParameters> void executeKeyDerivationTest(KeyDerivationAlgorithm<P> algorithm, byte[] data, P parameters, TestResult result) {
        try {
            byte[] key = deriveKey(algorithm, data, parameters, result.getPerformance());
            result.setCipherText(encodeBase64(key));
            result.setTimestamp(LocalDateTime.now());
        } catch (Exception e) {
            throw new InvalidParameterException(algorithm.getName(), e);
        }
    }

    private void executeHashTest(HashAlgorithm algorithm, byte[] bytes, TestResult result) {
        try {
            byte[] hash = hashData(algorithm, bytes, result.getPerformance());
            result.setCipherText(encodeBase64(hash));
            result.setTimestamp(LocalDateTime.now());
        } catch (Exception e) {
            throw new InvalidParameterException(algorithm.getName(), e);
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
