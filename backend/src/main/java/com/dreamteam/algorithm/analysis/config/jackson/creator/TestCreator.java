package com.dreamteam.algorithm.analysis.config.jackson.creator;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.fasterxml.jackson.databind.JsonNode;

public interface TestCreator<T extends Algorithm> {
    Class<T> getSupportedClass();
    Test create(JsonNode node, T algorithm);
}
