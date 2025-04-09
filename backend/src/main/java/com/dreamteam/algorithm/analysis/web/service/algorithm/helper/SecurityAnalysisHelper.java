package com.dreamteam.algorithm.analysis.web.service.algorithm.helper;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SecurityAnalysisHelper {
    public static double calculateEntropy(String input) {
        Map<Character, Long> frequencyMap = calculateFrequency(input);
        double entropy = 0.0;
        int length = input.length();
        for (long frequency : frequencyMap.values()) {
            double probability = (double) frequency / length;
            entropy -= probability * (Math.log(probability) / Math.log(2));
        }
        return entropy;
    }

    public static double calculateFrequencyScore(String data) {
        Map<Character, Long> charFrequencyMap = calculateFrequency(data);
        int totalChars = data.length();
        int uniqueChars = charFrequencyMap.size();
        double expectedFrequency = (double) totalChars / uniqueChars;
        double frequencyDifferenceSum = charFrequencyMap.values().stream()
                .mapToDouble(frequency -> Math.abs(frequency - expectedFrequency))
                .sum();
        return 1.0 - (frequencyDifferenceSum / totalChars);
    }

    private static Map<Character, Long> calculateFrequency(String data) {
        return data.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

}
