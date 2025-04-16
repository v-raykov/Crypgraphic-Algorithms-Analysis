package com.dreamteam.algorithm.analysis.config.jackson;

import com.dreamteam.algorithm.analysis.config.jackson.test.TestDeserializer;
import com.dreamteam.algorithm.analysis.config.jackson.test.TestFactory;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    @Bean
    public SimpleModule testModule(TestFactory testFactory) {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Test.class, new TestDeserializer(testFactory));
        return module;
    }
}
