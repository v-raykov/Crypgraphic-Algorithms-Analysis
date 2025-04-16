package com.dreamteam.algorithm.analysis.config.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    private static final ModelMapper modelMapper = new ModelMapper();
    @Bean
    public ModelMapper modelMapper() {
        return modelMapper;
    }
}
