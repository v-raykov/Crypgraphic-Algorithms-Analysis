package com.dreamteam.algorithm.analysis.domain.algorithm.impl.zero.knowedge;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.AlgorithmType;

import java.lang.reflect.Field;

public interface ZeroKnowledgeProofAlgorithm extends Algorithm {
    byte[] generateProof(byte[] secret, byte[] publicInput);
    boolean verifyProof(byte[] proof, byte[] publicInput);

    @Override
     default AlgorithmType getType() {
        return null;
    }

    @Override
     default Field[] getFields() {
        return new Field[0];
    }
}
