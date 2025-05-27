package com.dreamteam.algorithm.analysis.model.test;

import com.dreamteam.algorithm.analysis.model.test.key.pair.AlgorithmKeyPair;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.digital.signature.DigitalSignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DigitalSignatureTest extends Test {
    private DigitalSignatureAlgorithm algorithm;
    private String data;
    private AlgorithmKeyPair algorithmKeyPair;
}
