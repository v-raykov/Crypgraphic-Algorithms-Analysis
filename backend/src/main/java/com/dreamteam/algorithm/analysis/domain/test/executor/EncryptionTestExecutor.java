package com.dreamteam.algorithm.analysis.domain.test.executor;

import com.dreamteam.algorithm.analysis.config.exception.FaultyAlgorithmException;
import com.dreamteam.algorithm.analysis.config.exception.InvalidParameterException;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.parameter.EncryptionParameters;
import com.dreamteam.algorithm.analysis.model.TestResult;
import com.dreamteam.algorithm.analysis.model.benchmark.PerformanceBenchmark;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

import static com.dreamteam.algorithm.analysis.domain.test.executor.TestExecutorUtilities.encodeBase64;
import static com.dreamteam.algorithm.analysis.domain.test.executor.TestExecutorUtilities.executeSecurityBenchmarks;

@Component
public class EncryptionTestExecutor implements TestExecutor {
    @Override
    public <T extends Test> TestResult execute(T test) {
        return executeEncryption((EncryptionTest<?>) test);
    }

    @Override
    public Class<? extends Test> supports() {
        return EncryptionTest.class;
    }

    private <P extends EncryptionParameters> TestResult executeEncryption(EncryptionTest<P> test) {
        TestResult result = new TestResult(test);
        EncryptionAlgorithm<P> algorithm = test.getAlgorithm();
        byte[] data = test.getData().getBytes();
        P parameters = test.getParameters();

        try {
            byte[] encrypted = encryptData(algorithm, data, parameters, result.getPerformance());
            byte[] decrypted = decryptData(algorithm, encrypted, parameters, result.getPerformance());
            validateDecryption(data, decrypted, algorithm.getName());
            result.setCipherText(encodeBase64(encrypted));
            result.setTimestamp(LocalDateTime.now());

        } catch (Exception e) {
            throw new InvalidParameterException(algorithm.getName(), e);
        }
        executeSecurityBenchmarks(result);
        return result;
    }

    private <P extends EncryptionParameters> byte[] encryptData(EncryptionAlgorithm<P> algorithm, byte[] data, P parameters, PerformanceBenchmark benchmark) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.encrypt(data, parameters),
                benchmark::setCipherTime,
                benchmark::setCipherMemory
        );
    }

    private <P extends EncryptionParameters> byte[] decryptData(EncryptionAlgorithm<P> algorithm, byte[] data, P parameters, PerformanceBenchmark benchmark) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.decrypt(data, parameters),
                benchmark::setDecipherTime,
                benchmark::setDecipherMemory
        );
    }

    private void validateDecryption(byte[] decrypted, byte[] original, String algorithmName) {
        if (!Arrays.equals(decrypted, original)) {
            throw new FaultyAlgorithmException(algorithmName);
        }
    }
}
