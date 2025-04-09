package com.dreamteam.algorithm.analysis.web.service.algorithm;

import com.dreamteam.algorithm.analysis.model.dto.TestResultDto;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.dreamteam.algorithm.analysis.model.test.TestResult;
import com.dreamteam.algorithm.analysis.web.service.algorithm.result.storage.ResultStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final ExecutionService testService;
    private final ResultStorage resultStorage;

    public TestResultDto testAlgorithm(Test test) {
        var result = switch (test) {
            case EncryptionTest t -> testService.testEncryption(t);
            default -> throw new IllegalStateException("Unexpected value: " + test);
        };
        return resultStorage.addResult(result).toDto();
    }

    public TestResultDto getTestResultById(String id) {
        return resultStorage.getTestResultById(id).toDto();
    }

    public List<TestResultDto> getTestResults() {
        return resultStorage.getResults().stream()
                .map(TestResult::toDto)
                .toList();
    }
}
