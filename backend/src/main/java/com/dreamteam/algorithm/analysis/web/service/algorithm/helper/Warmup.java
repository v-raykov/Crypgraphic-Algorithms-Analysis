package com.dreamteam.algorithm.analysis.web.service.algorithm.helper;

import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.web.service.algorithm.AlgorithmService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Warmup {
    public Warmup(AlgorithmService algorithmService) {
        var test = new EncryptionTest();
        test.setAlgorithmName("blowFish");
        test.setPlaintext("warmup");
        algorithmService.testAlgorithm(test, Optional.empty());
    }
}
