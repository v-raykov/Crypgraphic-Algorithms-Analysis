package com.dreamteam.algorithm.analysis.model.test.factory;

import com.dreamteam.algorithm.analysis.config.exception.AlgorithmDoesNotExistsException;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class TestDeserializer extends JsonDeserializer<Test> {
    private final TestFactory factory;

    public TestDeserializer(TestFactory factory) {
        this.factory = factory;
    }

    @Override
    public Test deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        if (!node.has("algorithmName")) {
            throw new AlgorithmDoesNotExistsException("No algorithmName found in request");
        }
        return factory.createTestFromJson(node);
    }
}
