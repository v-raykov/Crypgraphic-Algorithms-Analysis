package com.dreamteam.algorithm.analysis.web.service.algorithm;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.BlockCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.parameter.EncryptionParametersFactory;
import com.dreamteam.algorithm.analysis.domain.test.executor.TestExecutor;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class WarmupService {
    private final AlgorithmService algorithmService;
    private final Map<Class<? extends Test>, TestExecutor> executors;

    @PostConstruct
    public void init() {
        BlockCipherEncryptionAlgorithm algorithm = (BlockCipherEncryptionAlgorithm) algorithmService.getAlgorithmByName("blowFish");
        EncryptionTest<?> test = new EncryptionTest<>(algorithm,"warmup", EncryptionParametersFactory.createBlockCipherEncryptionParameters(algorithm, null, 0, null));
        executors.get(test.getClass()).execute(test);
    }
}
