package com.dreamteam.algorithm.analysis.web.service.algorithm.helper;

import com.dreamteam.algorithm.analysis.config.exception.FaultyAlgorithmException;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base.KeyDerivationAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.parameter.KeyDerivationParameters;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.parameter.EncryptionParameters;
import com.dreamteam.algorithm.analysis.model.test.benchmark.PerformanceBenchmark;

import java.util.Arrays;

public class TestExecutionHelper {
    public static <P extends EncryptionParameters> byte[] encryptData(EncryptionAlgorithm<P> algorithm, byte[] data, P parameters, PerformanceBenchmark benchmark) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.encrypt(data, parameters),
                benchmark::setCipherTime,
                benchmark::setCipherMemory
        );
    }

    public static <P extends EncryptionParameters> byte[] decryptData(EncryptionAlgorithm<P> algorithm, byte[] data, P parameters, PerformanceBenchmark benchmark) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.decrypt(data, parameters),
                benchmark::setDecipherTime,
                benchmark::setDecipherMemory
        );
    }

    public static <P extends KeyDerivationParameters> byte[] deriveKey(KeyDerivationAlgorithm<P> algorithm, byte[] data, P parameters, PerformanceBenchmark benchmark) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.deriveKey(data, parameters),
                benchmark::setCipherTime,
                benchmark::setCipherMemory
        );
    }

    public static void validateDecryption(byte[] decrypted, byte[] original, String algorithmName) {
        if (!Arrays.equals(decrypted, original)) {
            throw new FaultyAlgorithmException(algorithmName);
        }
    }
}
