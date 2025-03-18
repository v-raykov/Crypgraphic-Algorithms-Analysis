package com.dreamteam.algorithm.analysis.domain.algorithm.digital.signature;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;

public interface DigitalSignitureAlgorithm extends Algorithm {
    byte[] sign(byte[] data, byte[] privateKey);
    boolean verify(byte[] data, byte[] signature, byte[] publicKey);
}
