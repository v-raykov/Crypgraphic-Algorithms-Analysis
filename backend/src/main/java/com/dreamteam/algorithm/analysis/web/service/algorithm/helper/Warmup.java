package com.dreamteam.algorithm.analysis.web.service.algorithm.helper;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.web.service.algorithm.ExecutionService;
import com.dreamteam.algorithm.analysis.web.service.algorithm.TestService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Warmup {
    private final TestService testService;
    private final ExecutionService executionService;

    @PostConstruct
    public void init() {
        var test = new EncryptionTest((EncryptionAlgorithm) testService.findAlgorithm("blowFish"), "warmup");
        executionService.testEncryption(test);
    }
}
