package com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmType;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.parameter.CountBasedKeyDerivationParameters;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.parameter.KeyDerivationParameters;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base.AlgorithmUtilities.secureRandom;

public abstract class CountBasedKeyDerivationAlgorithm implements KeyDerivationAlgorithm<CountBasedKeyDerivationParameters> {
    @Override
    public AlgorithmType getType() {
        return AlgorithmType.COUNT_BASED_KEY_DERIVATION;
    }

    @Override
    public Field[] getFields() {
        return Stream.concat(Arrays.stream(KeyDerivationParameters.class.getDeclaredFields()), Arrays.stream(CountBasedKeyDerivationParameters.class.getDeclaredFields()))
                .toArray(Field[]::new);
    }

    public boolean isValidIterations(int iterations) {
        return iterations >= 10000 && iterations <= 1000000;
    }

    public int getRandomIterations() {
        return secureRandom.nextInt(10000, 1000000);
    }
}
