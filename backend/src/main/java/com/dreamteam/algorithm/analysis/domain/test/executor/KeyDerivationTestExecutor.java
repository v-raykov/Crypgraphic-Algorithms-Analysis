package com.dreamteam.algorithm.analysis.domain.test.executor;

import com.dreamteam.algorithm.analysis.config.exception.InvalidParameterException;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base.KeyDerivationAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.parameter.KeyDerivationParameters;
import com.dreamteam.algorithm.analysis.model.TestResult;
import com.dreamteam.algorithm.analysis.model.benchmark.PerformanceBenchmark;
import com.dreamteam.algorithm.analysis.model.test.KeyDerivationTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.dreamteam.algorithm.analysis.domain.test.executor.TestExecutorUtilities.encodeBase64;
import static com.dreamteam.algorithm.analysis.domain.test.executor.TestExecutorUtilities.executeSecurityBenchmarks;

@Component
public class KeyDerivationTestExecutor implements TestExecutor {
    @Override
    public <T extends Test> TestResult execute(T test) {
        return testKeyDerivation((KeyDerivationTest<?>) test);
    }

    @Override
    public Class<? extends Test> supports() {
        return KeyDerivationTest.class;
    }

    public <P extends KeyDerivationParameters> TestResult testKeyDerivation(KeyDerivationTest<P> test) {
        TestResult result = new TestResult(test);
        KeyDerivationAlgorithm<P> algorithm = test.getAlgorithm();
        byte[] data = test.getData().getBytes();
        P parameters = test.getParameters();

        try {
            byte[] key = deriveKey(algorithm, data, parameters, result.getPerformance());
            result.setCipherText(encodeBase64(key));
            result.setTimestamp(LocalDateTime.now());
        } catch (Exception e) {
            throw new InvalidParameterException(algorithm.getName(), e);
        }
        executeSecurityBenchmarks(result);
        return result;
    }

    private <P extends KeyDerivationParameters> byte[] deriveKey(KeyDerivationAlgorithm<P> algorithm, byte[] data, P parameters, PerformanceBenchmark benchmark) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.deriveKey(data, parameters),
                benchmark::setCipherTime,
                benchmark::setCipherMemory
        );
    }
}
