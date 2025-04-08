package com.dreamteam.algorithm.analysis.web.service.algorithm.helper;

import com.dreamteam.algorithm.analysis.config.exception.FaultyAlgorithmException;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.option.RequiresIv;
import com.dreamteam.algorithm.analysis.model.test.TestResult;

import java.util.Arrays;

public class TestExecutionHelper {
    public static byte[] encryptData(EncryptionAlgorithm algorithm, String plaintext, byte[] key, TestResult result) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.encrypt(plaintext.getBytes(), key),
                result::setCipherTime,
                result::setCipherMemory
        );
    }

    public static byte[] decryptData(EncryptionAlgorithm algorithm, byte[] encrypted, byte[] key, TestResult result) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.decrypt(encrypted, key),
                result::setDecipherTime,
                result::setDecipherMemory
        );
    }

    public static void validateDecryption(byte[] decrypted, String original, String algorithmName) {
        if (!Arrays.equals(decrypted, original.getBytes())) {
            throw new FaultyAlgorithmException(algorithmName);
        }
    }

    public static void applyInitializationVectorIfNeeded(EncryptionAlgorithm algorithm, byte[] iv) {
        if (algorithm instanceof RequiresIv requiresIv) {
            requiresIv.setIv(iv);
        }
    }
}
