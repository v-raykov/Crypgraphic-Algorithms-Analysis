package com.dreamteam.algorithm.analysis.config.jackson.test.creator;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmType;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.fasterxml.jackson.databind.JsonNode;

public interface TestCreator<T extends Algorithm> {
    Test create(JsonNode node, T algorithm);
    AlgorithmType getType();
}
