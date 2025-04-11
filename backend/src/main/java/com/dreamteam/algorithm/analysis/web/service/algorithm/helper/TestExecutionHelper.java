package com.dreamteam.algorithm.analysis.web.service.algorithm.helper;

import com.dreamteam.algorithm.analysis.config.exception.FaultyAlgorithmException;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.model.test.benchmark.PerformanceBenchmark;

import java.util.Arrays;

public class TestExecutionHelper {
    public static byte[] encryptData(EncryptionAlgorithm algorithm, String plaintext, byte[] key, byte[] iv, PerformanceBenchmark benchmark) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.encrypt(plaintext.getBytes(), key, iv),
                benchmark::setCipherTime,
                benchmark::setCipherMemory
        );
    }

    public static byte[] decryptData(EncryptionAlgorithm algorithm, byte[] encrypted, byte[] key, byte[] iv, PerformanceBenchmark benchmark) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.decrypt(encrypted, key, iv),
                benchmark::setDecipherTime,
                benchmark::setDecipherMemory
        );
    }

    public static void validateDecryption(byte[] decrypted, String original, String algorithmName) {
        if (!Arrays.equals(decrypted, original.getBytes())) {
            throw new FaultyAlgorithmException(algorithmName);
        }
    }
}
