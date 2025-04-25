package com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmType;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.parameter.KeyDerivationParameters;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.parameter.ResourceBasedKeyDerivationParameters;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Stream;

public abstract class ResourceBasedKeyDerivationAlgorithm implements KeyDerivationAlgorithm<ResourceBasedKeyDerivationParameters> {
    @Override
    public Field[] getFields() {
        return Stream.concat(Arrays.stream(KeyDerivationParameters.class.getDeclaredFields()), Arrays.stream(ResourceBasedKeyDerivationParameters.class.getDeclaredFields()))
                .toArray(Field[]::new);
    }

    @Override
    public AlgorithmType getType() {
        return AlgorithmType.RESOURCE_BASED_KEY_DERIVATION;
    }
}
