package com.dreamteam.algorithm.analysis.config.jackson.test;

import com.dreamteam.algorithm.analysis.config.jackson.FieldNames;
import com.dreamteam.algorithm.analysis.config.jackson.test.creator.TestCreator;
import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.AlgorithmType;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.dreamteam.algorithm.analysis.web.service.algorithm.AlgorithmService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TestFactory {
    private final AlgorithmService algorithmService;
    private final Map<AlgorithmType, TestCreator<Algorithm>> creatorMap;

    public Test createTestFromJson(JsonNode node) {
        var algorithm = algorithmService.getAlgorithmByName(node.get(FieldNames.ALGORITHM.toString()).asText());
        var creator = creatorMap.get(algorithm.getType());
        return creator.create(node, algorithm);
    }
}
