package com.dreamteam.algorithm.analysis.config.jackson;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.Algorithm;
import com.dreamteam.algorithm.analysis.web.service.algorithm.AlgorithmService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class JacksonConfig {
    private final AlgorithmService algorithmService;

    @Bean
    public Function<String, Algorithm> getAlgorithmByName() {
        return algorithmService::getAlgorithmByName;
    }
}
