package com.dreamteam.algorithm.analysis.config.jackson.test.creator;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class CreatorConfig {
    private final List<TestCreator<?>> creators;

    @Bean
    @SuppressWarnings("unchecked")
    public Map<AlgorithmType, TestCreator<Algorithm>> creatorMap() {
        return creators.stream()
                .map(creator -> (TestCreator<Algorithm>) creator)
                .collect(Collectors.toUnmodifiableMap(
                        TestCreator::getType,
                        Function.identity()));
    }
}
