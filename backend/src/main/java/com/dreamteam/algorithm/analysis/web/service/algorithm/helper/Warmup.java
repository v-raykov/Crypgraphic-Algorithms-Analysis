package com.dreamteam.algorithm.analysis.web.service.algorithm.helper;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.web.service.algorithm.AlgorithmService;
import com.dreamteam.algorithm.analysis.web.service.algorithm.ExecutionService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Warmup {
    private final AlgorithmService algorithmService;
    private final ExecutionService executionService;

    @PostConstruct
    public void init() {
        var test = new EncryptionTest((EncryptionAlgorithm) algorithmService.findAlgorithm("blowFish"), "warmup");
        executionService.testEncryption(test);
    }
}
