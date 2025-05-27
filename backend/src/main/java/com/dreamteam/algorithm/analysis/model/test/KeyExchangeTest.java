package com.dreamteam.algorithm.analysis.model.test;

import com.dreamteam.algorithm.analysis.model.test.key.pair.AlgorithmKeyPair;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.exchange.key.KeyExchangeAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KeyExchangeTest extends Test {
    private KeyExchangeAlgorithm algorithm;
    private AlgorithmKeyPair keyPair;
}
