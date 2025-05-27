package com.dreamteam.algorithm.analysis.config.jackson.test.creator;

import com.dreamteam.algorithm.analysis.config.jackson.FieldNames;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmType;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash.HashAlgorithm;
import com.dreamteam.algorithm.analysis.model.test.HashTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class HashTestCreator implements TestCreator<HashAlgorithm> {
    private final AlgorithmType type = AlgorithmType.HASH;

    @Override
    public Test create(JsonNode node, HashAlgorithm algorithm) {
        return new HashTest(algorithm, node.get(FieldNames.DATA.toString()).asText());
    }
}
