package com.dreamteam.algorithm.analysis.web.service.algorithm;

import com.dreamteam.algorithm.analysis.config.exception.AlgorithmDoesNotExistsException;
import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.dreamteam.algorithm.analysis.model.test.TestResult;
import com.dreamteam.algorithm.analysis.repository.TestResultRepository;
import com.dreamteam.algorithm.analysis.web.service.algorithm.result.storage.ResultStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TestService {
    private final Map<String, Algorithm> algorithms;
    private final ExecutionService testService;
    private final TestResultRepository resultRepository;

    public TestResult testAlgorithm(Test test, ResultStorage storage) {
        var result = switch (test) {
            case EncryptionTest t -> testService.testEncryption(t);
            default -> throw new IllegalStateException("Unexpected value: " + test);
        };
        return saveResult(result, storage);
    }

    public Algorithm findAlgorithm(String algorithmName) {
        var algorithm = algorithms.get(algorithmName);
        if (algorithm == null) {
            throw new AlgorithmDoesNotExistsException(algorithmName);
        }
        return algorithm;
    }

    private TestResult saveResult(TestResult result, ResultStorage storage) {
        var savedResult = resultRepository.save(result);
        storage.addTestResult(savedResult);
        return savedResult;
    }

}
