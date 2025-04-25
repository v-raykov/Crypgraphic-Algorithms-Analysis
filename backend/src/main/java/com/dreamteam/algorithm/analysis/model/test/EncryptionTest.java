package com.dreamteam.algorithm.analysis.model.test;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.parameter.EncryptionParameters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EncryptionTest<P extends EncryptionParameters> extends Test {
    private EncryptionAlgorithm<P> algorithm;
    private String plaintext;
    private P parameters;
}
