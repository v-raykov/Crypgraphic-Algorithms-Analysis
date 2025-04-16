package com.dreamteam.algorithm.analysis.config.jackson;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.AlgorithmType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class AlgorithmSerializer extends JsonSerializer<Algorithm> {
    private static final List<String> excludedFields = List.of(FieldNames.RANDOM_KEY.toString(), FieldNames.ENGINE.toString());

    @Override
    public void serialize(Algorithm algorithm, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("name", algorithm.getName());
        gen.writeStringField("type", AlgorithmType.fromAlgorithm(algorithm).toString());
        Field[] fields = algorithm.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (!excludedFields.contains(field.getName())) {
                try {
                    gen.writeObjectField(field.getName(), field.get(algorithm));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        gen.writeEndObject();
    }
}
