package com.dreamteam.algorithm.analysis.domain.test.executor;

import com.dreamteam.algorithm.analysis.config.exception.InvalidParameterException;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash.HashAlgorithm;
import com.dreamteam.algorithm.analysis.model.TestResult;
import com.dreamteam.algorithm.analysis.model.benchmark.PerformanceBenchmark;
import com.dreamteam.algorithm.analysis.model.test.HashTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

import static com.dreamteam.algorithm.analysis.domain.test.executor.TestExecutorUtilities.encodeBase64;
import static com.dreamteam.algorithm.analysis.domain.test.executor.TestExecutorUtilities.executeSecurityBenchmarks;

@Configuration
public class HashTestExecutor implements TestExecutor {
    @Override
    public <T extends Test> TestResult execute(T test) {
        return testHash((HashTest) test);
    }

    @Override
    public Class<? extends Test> supports() {
        return HashTest.class;
    }

    private TestResult testHash(HashTest test) {
        TestResult result = new TestResult(test);
        HashAlgorithm algorithm = test.getAlgorithm();
        byte[] data = test.getData().getBytes();

        try {
            byte[] hash = hashData(algorithm, data, result.getPerformance());
            result.setCipherText(encodeBase64(hash));
            result.setTimestamp(LocalDateTime.now());
        } catch (Exception e) {
            throw new InvalidParameterException(algorithm.getName(), e);
        }

        executeSecurityBenchmarks(result);
        return result;
    }


    private byte[] hashData(HashAlgorithm algorithm, byte[] data, PerformanceBenchmark benchmark) throws Exception {
        return PerformanceMonitor.measureExecution(() -> algorithm.hash(data),
                benchmark::setCipherTime,
                benchmark::setCipherMemory);
    }
}
