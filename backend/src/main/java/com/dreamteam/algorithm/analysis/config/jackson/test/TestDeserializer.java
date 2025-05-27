package com.dreamteam.algorithm.analysis.config.jackson.test;

import com.dreamteam.algorithm.analysis.config.jackson.FieldNames;
import com.dreamteam.algorithm.analysis.config.jackson.test.creator.TestCreator;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmType;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;

import java.io.IOException;
import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
public class TestDeserializer extends JsonDeserializer<Test> {
    private final Map<AlgorithmType, TestCreator<Algorithm>> creatorMap;
    private final Function<String, Algorithm> getAlgorithmByName;

    @Override
    public Test deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        if (!node.has(FieldNames.ALGORITHM.toString())) {
            throw new BadRequestException("Please specify algorithm to test!");
        }
        return createTestFromJson(node);
    }

    private Test createTestFromJson(JsonNode node) {
        var algorithm = getAlgorithmByName.apply(node.get(FieldNames.ALGORITHM.toString()).asText());
        var creator = creatorMap.get(algorithm.getType());
        return creator.create(node, algorithm);
    }
}
