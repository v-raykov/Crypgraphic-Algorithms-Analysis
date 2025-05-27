package com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.parameter.KeyDerivationParameters;

import static com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmUtilities.secureRandom;

public interface KeyDerivationAlgorithm<P extends KeyDerivationParameters> extends Algorithm {
    byte[] deriveKey(byte[] password, P parameters);

    default boolean isValidSaltSize(int saltSize) {
        return saltSize >= 8 && saltSize <= 64;
    }

    default int getRandomSaltSize() {
        return secureRandom.nextInt(8, 64);
    }
}
