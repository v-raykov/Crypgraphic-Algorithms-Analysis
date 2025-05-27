package com.dreamteam.algorithm.analysis.web.service.algorithm;

import com.dreamteam.algorithm.analysis.model.dto.TestResultDto;
import com.dreamteam.algorithm.analysis.model.test.*;
import com.dreamteam.algorithm.analysis.web.service.algorithm.result.storage.ResultStorage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final ExecutionService testService;
    private final ResultStorage resultStorage;
    private final ModelMapper modelMapper;

    public TestResultDto testAlgorithm(Test test) {
        var result = switch (test) {
            case EncryptionTest<?> t -> testService.testEncryption(t);
            case KeyDerivationTest<?> t -> testService.testKeyDerivation(t);
            case DigitalSignatureTest t -> testService.testDigitalSignature(t);
            case KeyExchangeTest t -> testService.testKeyExchange(t);
            case HashTest t -> testService.testHash(t);
            default -> throw new IllegalStateException("Unexpected value: " + test);
        };
        return modelMapper.map(resultStorage.addResult(result), TestResultDto.class);
    }

    public TestResultDto getTestResultById(String id) {
        return modelMapper.map(resultStorage.getResultById(id), TestResultDto.class);
    }

    public List<TestResultDto> getTestResults() {
        return resultStorage.getResults().stream()
                .map(result -> modelMapper.map(result, TestResultDto.class))
                .toList();
    }
}
