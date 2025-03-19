package com.dreamteam.algorithm.analysis.domain.algorithm.impl.zero.knowedge;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;

public interface ZeroKnowledgeProofAlgorithm extends Algorithm {
    byte[] generateProof(byte[] secret, byte[] publicInput);
    boolean verifyProof(byte[] proof, byte[] publicInput);
}
