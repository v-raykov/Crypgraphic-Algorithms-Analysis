package com.dreamteam.algorithm.analysis.web.service.algorithm.helper;

import com.dreamteam.algorithm.analysis.config.exception.FaultyAlgorithmException;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionParameters;
import com.dreamteam.algorithm.analysis.model.test.benchmark.PerformanceBenchmark;

import java.util.Arrays;

public class TestExecutionHelper {
    public static <T extends EncryptionParameters> byte[] encryptData(EncryptionAlgorithm<T> algorithm, byte[] data, T parameters, PerformanceBenchmark benchmark) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.encrypt(data, parameters),
                benchmark::setCipherTime,
                benchmark::setCipherMemory
        );
    }

    public static <T extends EncryptionParameters> byte[] decryptData(EncryptionAlgorithm<T> algorithm, byte[] data, T parameters, PerformanceBenchmark benchmark) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.decrypt(data, parameters),
                benchmark::setDecipherTime,
                benchmark::setDecipherMemory
        );
    }

    public static void validateDecryption(byte[] decrypted, byte[] original, String algorithmName) {
        if (!Arrays.equals(decrypted, original)) {
            throw new FaultyAlgorithmException(algorithmName);
        }
    }
}
