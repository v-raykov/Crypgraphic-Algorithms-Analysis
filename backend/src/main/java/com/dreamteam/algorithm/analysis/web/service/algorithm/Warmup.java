package com.dreamteam.algorithm.analysis.web.service.algorithm;

import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import org.springframework.stereotype.Component;

@Component
public class Warmup {
    public Warmup(AlgorithmService algorithmService) {
        var test = new EncryptionTest();
        test.setAlgorithmName("blowFish");
        test.setPlaintext("warmup");
        algorithmService.testAlgorithm(test);
    }
}
