package com.dreamteam.algorithm.analysis.domain.test.executor;

import com.dreamteam.algorithm.analysis.config.exception.InvalidParameterException;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.exchange.key.KeyExchangeAlgorithm;
import com.dreamteam.algorithm.analysis.model.TestResult;
import com.dreamteam.algorithm.analysis.model.benchmark.PerformanceBenchmark;
import com.dreamteam.algorithm.analysis.model.test.KeyExchangeTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.dreamteam.algorithm.analysis.model.test.key.pair.AlgorithmKeyPair;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.dreamteam.algorithm.analysis.domain.test.executor.TestExecutorUtilities.encodeBase64;
import static com.dreamteam.algorithm.analysis.domain.test.executor.TestExecutorUtilities.executeSecurityBenchmarks;

@Component
public class KeyExchangeTestExecutor implements TestExecutor {
    @Override
    public <T extends Test> TestResult execute(T test) {
        return testKeyExchange((KeyExchangeTest) test);
    }

    @Override
    public Class<? extends Test> supports() {
        return KeyExchangeTest.class;
    }

    private TestResult testKeyExchange(KeyExchangeTest test) {
        TestResult result = new TestResult(test);
        KeyExchangeAlgorithm algorithm = test.getAlgorithm();
        AlgorithmKeyPair keyPair = test.getKeyPair();

        try {
            byte[] shared = deriveSharedSecret(algorithm, keyPair, result.getPerformance());
            result.setCipherText(encodeBase64(shared));
            result.setTimestamp(LocalDateTime.now());
        } catch (Exception e) {
            throw new InvalidParameterException(algorithm.getName(), e);
        }
        executeSecurityBenchmarks(result);
        return result;
    }

    private byte[] deriveSharedSecret(KeyExchangeAlgorithm algorithm, AlgorithmKeyPair algorithmKeyPair, PerformanceBenchmark benchmark) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.deriveSharedSecret(algorithmKeyPair.getPublicKey(), algorithmKeyPair.getPrivateKey()),
                benchmark::setCipherTime,
                benchmark::setCipherMemory);
    }
}
