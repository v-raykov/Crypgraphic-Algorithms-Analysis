package com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmType;

import java.lang.reflect.Field;

public interface HashAlgorithm extends Algorithm {
    byte[] hash(byte[] data);

    @Override
     default AlgorithmType getType() {
        return null;
    }

    @Override
     default Field[] getFields() {
        return new Field[0];
    }
}
