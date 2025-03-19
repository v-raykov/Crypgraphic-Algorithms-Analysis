package com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;

public interface HashAlgorithm extends Algorithm {
    byte[] hash(byte[] data);
}
