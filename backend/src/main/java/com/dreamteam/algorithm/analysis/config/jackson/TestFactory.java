package com.dreamteam.algorithm.analysis.config.jackson;

import com.dreamteam.algorithm.analysis.config.jackson.creator.TestCreator;
import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
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
    private final Map<Class<?>, TestCreator<Algorithm>> creatorMap;

    public Test createTestFromJson(JsonNode node) {
        var algorithm = algorithmService.getAlgorithmByName(node.get("algorithm").asText());
        TestCreator<Algorithm> creator = creatorMap.get(getParentClass(algorithm));
        if (creator != null) {
            return creator.create(node, algorithm);
        } else {
            throw new IllegalStateException("No TestCreator found for algorithm: " + getParentClass(algorithm));
        }
    }

    private Class<?> getParentClass(Algorithm algorithm) {
        var parentClass = algorithm.getClass().getSuperclass();
        if (parentClass == null) {
            Class<?>[] interfaces = algorithm.getClass().getInterfaces();
            parentClass = (interfaces.length > 0) ? interfaces[0] : null;
        }

        if (parentClass == null) {
            throw new IllegalStateException("Algorithm does not have a valid parent class or interface.");
        }
        return parentClass;
    }
}
