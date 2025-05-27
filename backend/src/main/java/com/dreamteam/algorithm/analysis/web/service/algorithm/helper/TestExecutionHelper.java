package com.dreamteam.algorithm.analysis.web.service.algorithm.helper;

import com.dreamteam.algorithm.analysis.config.exception.FaultyAlgorithmException;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base.KeyDerivationAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.parameter.KeyDerivationParameters;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.digital.signature.DigitalSignatureAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.parameter.EncryptionParameters;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.exchange.key.KeyExchangeAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash.HashAlgorithm;
import com.dreamteam.algorithm.analysis.model.benchmark.PerformanceBenchmark;
import com.dreamteam.algorithm.analysis.model.test.key.pair.AlgorithmKeyPair;

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

    public static void validateDecryption(byte[] decrypted, byte[] original, String algorithmName) {
        if (!Arrays.equals(decrypted, original)) {
            throw new FaultyAlgorithmException(algorithmName);
        }
    }

    public static <P extends KeyDerivationParameters> byte[] deriveKey(KeyDerivationAlgorithm<P> algorithm, byte[] data, P parameters, PerformanceBenchmark benchmark) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.deriveKey(data, parameters),
                benchmark::setCipherTime,
                benchmark::setCipherMemory
        );
    }

    public static byte[] signData(DigitalSignatureAlgorithm algorithm, byte[] bytes, AlgorithmKeyPair algorithmKeyPair, PerformanceBenchmark benchmark) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.sign(bytes, algorithmKeyPair.getPrivateKey()),
                benchmark::setCipherTime,
                benchmark::setCipherMemory);
    }

    public static void validateSignature(DigitalSignatureAlgorithm algorithm, byte[] bytes, byte[] signature, AlgorithmKeyPair algorithmKeyPair, PerformanceBenchmark benchmark) throws Exception {
        PerformanceMonitor.measureExecution(() -> algorithm.verify(bytes, signature, algorithmKeyPair.getPublicKey()),
                benchmark::setCipherTime,
                benchmark::setCipherMemory);
    }

    public static byte[] deriveSharedSecret(KeyExchangeAlgorithm algorithm, AlgorithmKeyPair algorithmKeyPair, PerformanceBenchmark benchmark) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.deriveSharedSecret(algorithmKeyPair.getPublicKey(), algorithmKeyPair.getPrivateKey()),
                benchmark::setCipherTime,
                benchmark::setCipherMemory);
    }

    public static byte[] hashData(HashAlgorithm algorithm, byte[] data, PerformanceBenchmark benchmark) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.hash(data),
                benchmark::setCipherTime,
                benchmark::setCipherMemory);
    }
}
