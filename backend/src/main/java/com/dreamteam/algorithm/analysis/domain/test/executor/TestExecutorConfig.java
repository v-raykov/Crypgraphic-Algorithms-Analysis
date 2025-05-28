package com.dreamteam.algorithm.analysis.domain.test.executor;

import com.dreamteam.algorithm.analysis.model.test.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class TestExecutorConfig {
    private final List<TestExecutor> executors;
    @Bean
    public Map<Class<? extends Test>, TestExecutor> executors() {
        return executors.stream()
                .collect(Collectors.toUnmodifiableMap(TestExecutor::supports, Function.identity()));
    }
}
