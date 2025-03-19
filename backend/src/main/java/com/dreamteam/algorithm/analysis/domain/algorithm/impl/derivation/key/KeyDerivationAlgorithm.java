package com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;

public interface KeyDerivationAlgorithm extends Algorithm {
    byte[] deriveKey(char[] password, byte[] salt, int iterations, int keyLength);
}
