package com.dreamteam.algorithm.analysis.domain.test.executor;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class SecurityAnalyzer {
    static double calculateEntropy(String data) {
        Map<Character, Long> frequencyMap = calculateFrequencyMap(data);
        double entropy = 0.0;
        int length = data.length();
        for (long frequency : frequencyMap.values()) {
            double probability = (double) frequency / length;
            entropy -= probability * (Math.log(probability) / Math.log(2));
        }
        return entropy;
    }

    static double calculateFrequencyScore(String data) {
        Map<Character, Long> frequencyMap = calculateFrequencyMap(data);
        int totalChars = data.length();
        int uniqueChars = frequencyMap.size();
        double expectedFrequency = (double) totalChars / uniqueChars;
        double frequencyDifferenceSum = frequencyMap.values().stream()
                .mapToDouble(frequency -> Math.abs(frequency - expectedFrequency))
                .sum();
        return 1.0 - (frequencyDifferenceSum / totalChars);
    }

    static Map<Character, Long> calculateFrequencyMap(String data) {
        return data.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

}
