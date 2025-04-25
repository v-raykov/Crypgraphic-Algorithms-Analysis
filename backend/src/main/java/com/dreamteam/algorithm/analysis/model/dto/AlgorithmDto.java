package com.dreamteam.algorithm.analysis.model.dto;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmType;
import lombok.Getter;

import java.util.List;

@Getter
public class AlgorithmDto {
    private final String name;
    private final AlgorithmType type;
    private final List<String> parameters;

    public AlgorithmDto(Algorithm algorithm) {
        name = algorithm.getName();
        type = AlgorithmType.fromAlgorithm(algorithm);
        parameters = algorithm.getParameters();
    }
}
