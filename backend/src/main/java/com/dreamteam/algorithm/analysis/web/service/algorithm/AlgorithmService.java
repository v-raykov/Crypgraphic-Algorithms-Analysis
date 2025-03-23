package com.dreamteam.algorithm.analysis.web.service.algorithm;

import com.dreamteam.algorithm.analysis.config.exception.AlgorithmDoesNotExistsException;
import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.model.User;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.dreamteam.algorithm.analysis.model.test.TestResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlgorithmService {
    private final Map<String, Algorithm> algorithms;
    private final TestService testService;

    public TestResult testAlgorithm(Test test, Optional<User> user) {
        var algorithm = findAlgorithm(test.getAlgorithmName());
        var result = switch (algorithm) {
            case EncryptionAlgorithm a -> testEncryption(a, test);
            default -> throw new IllegalStateException("Unexpected value: " + algorithm);
        };
        return result;
    }

    private TestResult testEncryption(EncryptionAlgorithm encryptionAlgorithm, Test test) {
        var encryptionTest = (EncryptionTest) test;
        encryptionTest.setDefaultValues(encryptionAlgorithm);
        return testService.testEncryption(encryptionAlgorithm, encryptionTest);
    }

    public Algorithm findAlgorithm(String algorithmName) {
        var algorithm = algorithms.get(algorithmName.trim());
        if (algorithm == null) {
            throw new AlgorithmDoesNotExistsException(algorithmName);
        }
        return algorithm;
    }

}
