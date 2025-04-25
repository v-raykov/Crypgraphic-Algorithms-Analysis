package com.dreamteam.algorithm.analysis.domain.algorithm.base;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public interface Algorithm {
    default String getName() {
        var className = getClass().getSimpleName();
        return Character.toLowerCase(className.charAt(0)) + className.substring(1);
    }

    default List<String> getParameters() {
        return Arrays.stream(getFields())
                .map(Field::getName)
                .toList();
    }

    AlgorithmType getType();

    Field[] getFields();
}
