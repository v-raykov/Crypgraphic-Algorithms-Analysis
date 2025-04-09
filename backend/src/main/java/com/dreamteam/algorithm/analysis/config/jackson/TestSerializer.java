package com.dreamteam.algorithm.analysis.config.jackson;

import com.dreamteam.algorithm.analysis.model.test.Test;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.lang.reflect.Field;

public class TestSerializer extends JsonSerializer<Test> {
    @Override
    public void serialize(Test test, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("algorithm", test.getAlgorithm().getName());
        Field[] fields = test.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (!field.getName().equals("algorithm")) {
                try {
                    gen.writeObjectField(field.getName(), field.get(test));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        gen.writeEndObject();
    }
}
