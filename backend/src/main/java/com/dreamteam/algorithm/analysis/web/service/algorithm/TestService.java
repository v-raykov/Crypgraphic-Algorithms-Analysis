package com.dreamteam.algorithm.analysis.web.service.algorithm;

import com.dreamteam.algorithm.analysis.model.TestResult;
import com.dreamteam.algorithm.analysis.model.dto.TestResultDto;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.dreamteam.algorithm.analysis.domain.test.executor.TestExecutor;
import com.dreamteam.algorithm.analysis.web.service.algorithm.result.storage.ResultStorage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TestService {
    private final ResultStorage resultStorage;
    private final ModelMapper modelMapper;

    private final Map<Class<? extends Test>, TestExecutor> executors;

    public TestResultDto performTest(Test test) {
        TestResult result = executors.get(test.getClass()).execute(test);
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
