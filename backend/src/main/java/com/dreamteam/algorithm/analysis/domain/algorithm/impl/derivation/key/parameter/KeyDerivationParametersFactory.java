package com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.parameter;

import com.dreamteam.algorithm.analysis.config.exception.InvalidParameterException;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base.CountBasedKeyDerivationAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base.KeyDerivationAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base.ResourceBasedKeyDerivationAlgorithm;

import static com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base.AlgorithmUtilities.*;

public class KeyDerivationParametersFactory {
    public static ResourceBasedKeyDerivationParameters createResourceBasedKeyDerivationParameters(ResourceBasedKeyDerivationAlgorithm algorithm, byte[] salt, int keySize, int costFactor, int blockSize, int parallelization) {
        return new ResourceBasedKeyDerivationParameters(
                processSalt(algorithm, salt),
                processKeySize(algorithm, keySize),
                processCostFactor(algorithm, costFactor),
                processBlockSize(algorithm, blockSize),
                processParallelization(algorithm, parallelization)
        );
    }

    public static CountBasedKeyDerivationParameters createCountBasedKeyDerivationParameters(CountBasedKeyDerivationAlgorithm algorithm, byte[] salt, int keySize, int iterations) {
        return new CountBasedKeyDerivationParameters(
                processSalt(algorithm, salt),
                processKeySize(algorithm, keySize),
                processIterations(algorithm, iterations));
    }

    private static int processIterations(CountBasedKeyDerivationAlgorithm algorithm, int iterations) {
        if (iterations != 0) {
            validateIterations(algorithm, iterations);
            return iterations;
        }
        return algorithm.getRandomIterations();
    }

    private static void validateIterations(CountBasedKeyDerivationAlgorithm algorithm, int iterations) {
        if (!algorithm.isValidIterations(iterations)) {
            throw new InvalidParameterException(algorithm.getName(), "iterations", iterations);
        }
    }

    private static byte[] processSalt(KeyDerivationAlgorithm<?> algorithm, byte[] salt) {
        if (salt != null) {
            validateSaltLength(algorithm, salt);
            return salt;
        }
        salt = new byte[algorithm.getRandomSaltSize()];
        secureRandom.nextBytes(salt);
        return salt;
    }

    private static int processKeySize(KeyDerivationAlgorithm<?> algorithm, int keySize) {
        if (keySize != 0) {
            validateKeySize(algorithm, keySize);
            return keySize;
        }
        return getRandomKeySize(algorithm);
    }

    private static int processCostFactor(ResourceBasedKeyDerivationAlgorithm algorithm, int costFactor) {
        if (costFactor != 0) {
            validateCostFactor(algorithm, costFactor);
            return costFactor;
        }
        return secureRandom.nextInt(12, 20);
    }

    private static int processBlockSize(ResourceBasedKeyDerivationAlgorithm algorithm, int blockSize) {
        if (blockSize != 0) {
            validateBlockSize(algorithm, blockSize);
            return blockSize;
        }
        return secureRandom.nextInt(8, 64);
    }

    private static int processParallelization(ResourceBasedKeyDerivationAlgorithm algorithm, int parallelization) {
        if (parallelization != 0) {
            validateParallelization(algorithm, parallelization);
            return parallelization;
        }
        return secureRandom.nextInt(1, 8);
    }

    private static void validateSaltLength(KeyDerivationAlgorithm<?> algorithm, byte[] salt) {
        if (!algorithm.isValidSaltSize(salt.length)) {
            throw new InvalidParameterException(algorithm.getName(), "salt", salt.length);
        }
    }

    private static void validateParallelization(ResourceBasedKeyDerivationAlgorithm algorithm, int parallelization) {
        if (parallelization < 1 || parallelization > 8) {
            throw new InvalidParameterException(algorithm.getName(), "parallelization", parallelization);
        }
    }

    private static void validateCostFactor(ResourceBasedKeyDerivationAlgorithm algorithm, int costFactor) {
        if (costFactor < 12 || costFactor > 20) {
            throw new InvalidParameterException(algorithm.getName(), "costFactor",  costFactor);
        }
    }

    private static void validateBlockSize(ResourceBasedKeyDerivationAlgorithm algorithm, int blockSize) {
        if (blockSize < 8 || blockSize > 64) {
            throw new InvalidParameterException(algorithm.getName(), "blockSize", blockSize);
        }
    }
}
