package com.dreamteam.algorithm.analysis.model.test;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.digital.signature.DigitalSignatureAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.digital.signature.parameter.DigitalSignatureKeyPair;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DigitalSignatureTest extends Test {
    private DigitalSignatureAlgorithm algorithm;
    private String data;
    private DigitalSignatureKeyPair keyPair;
}
