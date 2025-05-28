package com.dreamteam.algorithm.analysis.domain.test.executor;

import com.dreamteam.algorithm.analysis.config.exception.InvalidParameterException;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.digital.signature.DigitalSignatureAlgorithm;
import com.dreamteam.algorithm.analysis.model.TestResult;
import com.dreamteam.algorithm.analysis.model.benchmark.PerformanceBenchmark;
import com.dreamteam.algorithm.analysis.model.test.DigitalSignatureTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.dreamteam.algorithm.analysis.model.test.key.pair.AlgorithmKeyPair;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.dreamteam.algorithm.analysis.domain.test.executor.TestExecutorUtilities.encodeBase64;
import static com.dreamteam.algorithm.analysis.domain.test.executor.TestExecutorUtilities.executeSecurityBenchmarks;

@Component
public class DigitalSignatureTestExecutor implements TestExecutor {
    @Override
    public <T extends Test> TestResult execute(T test) {
        return testDigitalSignature((DigitalSignatureTest) test);
    }

    @Override
    public Class<? extends Test> supports() {
        return DigitalSignatureTest.class;
    }

    public TestResult testDigitalSignature(DigitalSignatureTest test) {
        System.out.println("executor");
        TestResult result = new TestResult(test);
        DigitalSignatureAlgorithm algorithm = test.getAlgorithm();
        byte[] data = test.getData().getBytes();
        AlgorithmKeyPair keyPair = test.getAlgorithmKeyPair();

        try {
            byte[] signature = signData(algorithm, data, keyPair, result.getPerformance());
            validateSignature(algorithm, data, signature, keyPair, result.getPerformance());
            result.setCipherText(encodeBase64(signature));
            result.setTimestamp(LocalDateTime.now());
        } catch (Exception e) {
            throw new InvalidParameterException(algorithm.getName(), e);
        }
        executeSecurityBenchmarks(result);
        return result;
    }

    private byte[] signData(DigitalSignatureAlgorithm algorithm, byte[] bytes, AlgorithmKeyPair algorithmKeyPair, PerformanceBenchmark benchmark) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.sign(bytes, algorithmKeyPair.getPrivateKey()),
                benchmark::setCipherTime,
                benchmark::setCipherMemory);
    }

    private void validateSignature(DigitalSignatureAlgorithm algorithm, byte[] bytes, byte[] signature, AlgorithmKeyPair algorithmKeyPair, PerformanceBenchmark benchmark) throws Exception {
        PerformanceMonitor.measureExecution(() -> algorithm.verify(bytes, signature, algorithmKeyPair.getPublicKey()),
                benchmark::setDecipherTime,
                benchmark::setDecipherMemory);
    }
}
