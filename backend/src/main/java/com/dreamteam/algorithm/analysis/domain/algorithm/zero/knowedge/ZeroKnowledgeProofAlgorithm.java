package com.dreamteam.algorithm.analysis.domain.algorithm.zero.knowedge;

public interface ZeroKnowledgeProofAlgorithm {
    byte[] generateProof(byte[] secret, byte[] publicInput);
    boolean verifyProof(byte[] proof, byte[] publicInput);
}
