package com.dreamteam.algorithm.analysis.web.service.algorithm;

import com.dreamteam.algorithm.analysis.config.exception.not.found.AlgorithmNotFoundException;
import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.AlgorithmType;
import com.dreamteam.algorithm.analysis.model.dto.AlgorithmDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlgorithmService {
    private final Map<String, Algorithm> algorithms;

    public Algorithm getAlgorithmByName(String algorithmName) {
        var algorithm = algorithms.get(algorithmName);
        if (algorithm == null) {
            throw new AlgorithmNotFoundException(algorithmName);
        }
        return algorithm;
    }
    public List<AlgorithmDto> getAlgorithms() {
        return algorithms.values().stream()
                .map(AlgorithmDto::new)
                .toList();
    }

    public List<AlgorithmDto> getAlgorithmsByType(AlgorithmType algorithmType) {
        return algorithms.values().stream()
                .filter(algorithm -> AlgorithmType.fromAlgorithm(algorithm) == algorithmType)
                .map(AlgorithmDto::new)
                .collect(Collectors.toList());
    }

    public AlgorithmDto getAlgorithmDtoByName(String name) {
        return new AlgorithmDto(getAlgorithmByName(name));
    }
}
