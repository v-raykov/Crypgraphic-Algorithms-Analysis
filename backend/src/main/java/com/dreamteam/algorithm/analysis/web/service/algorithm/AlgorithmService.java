package com.dreamteam.algorithm.analysis.web.service.algorithm;

import com.dreamteam.algorithm.analysis.config.exception.not.found.AlgorithmNotFoundException;
import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.AlgorithmType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlgorithmService {
    private final Map<String, Algorithm> algorithms;

    public Algorithm findAlgorithm(String algorithmName) {
        var algorithm = algorithms.get(algorithmName);
        if (algorithm == null) {
            throw new AlgorithmNotFoundException(algorithmName);
        }
        return algorithm;
    }
    public List<Algorithm> getAlgorithms() {
        return List.copyOf(algorithms.values());
    }

    public List<Algorithm> getAlgorithmsByType(AlgorithmType algorithmType) {
        return algorithms.values().stream()
                .filter(algorithm -> AlgorithmType.fromAlgorithm(algorithm) == algorithmType)
                .collect(Collectors.toList());

    }
}
