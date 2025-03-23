package com.dreamteam.algorithm.analysis.web.service.algorithm;

import com.dreamteam.algorithm.analysis.config.exception.FaultyAlgorithmException;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.option.RequiresIv;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTestResult;

import java.util.Arrays;

public class TestExecutionHelper {
    public static byte[] encryptData(EncryptionAlgorithm algorithm, String plaintext, byte[] key, EncryptionTestResult result) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.encrypt(plaintext.getBytes(), key),
                result::setEncryptionTime,
                result::setEncryptionMemory
        );
    }

    public static byte[] decryptData(EncryptionAlgorithm algorithm, byte[] encrypted, byte[] key, EncryptionTestResult result) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.decrypt(encrypted, key),
                result::setDecryptionTime,
                result::setDecryptionMemory
        );
    }

    public static void validateDecryption(byte[] decrypted, String original, String algorithmName) {
        if (!Arrays.equals(decrypted, original.getBytes())) {
            throw new FaultyAlgorithmException(algorithmName);
        }
    }

    public static void applyInitializationVectorIfNeeded(EncryptionAlgorithm algorithm, EncryptionTest test) {
        if (algorithm instanceof RequiresIv requiresIv) {
            requiresIv.setIv(test.getIv());
        }
    }
}
