package com.dreamteam.algorithm.analysis.config.jackson;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class AlgorithmSerializer extends JsonSerializer<Algorithm> {
    @Override
    public void serialize(Algorithm algorithm, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(algorithm.getName());
    }
}
