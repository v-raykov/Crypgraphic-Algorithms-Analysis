package com.dreamteam.algorithm.analysis.config.jackson;

import com.dreamteam.algorithm.analysis.config.jackson.creator.TestCreator;
import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class JacksonConfig {
    private final List<TestCreator<?>> creators;

    @Bean
    public SimpleModule testModule(TestFactory testFactory) {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Test.class, new TestDeserializer(testFactory));
        return module;
    }

    @Bean
    public Map<Class<?>, TestCreator<Algorithm>> creatorMap() {
        System.out.println(creators);
        return creators.stream()
                .map(creator -> (TestCreator<Algorithm>) creator)
                .collect(Collectors.toUnmodifiableMap(
                        TestCreator::getSupportedClass,
                        Function.identity()));
    }
}
