package com.dreamteam.algorithm.analysis.model.test;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionParameters;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EncryptionTest<T extends EncryptionParameters> implements Test {
    private EncryptionAlgorithm<T> algorithm;

    private String plaintext;
    private T parameters;

    public EncryptionTest(EncryptionAlgorithm<T> algorithm, String plaintext, T parameters) {
        this.algorithm = algorithm;
        this.plaintext = plaintext;
        this.parameters = parameters;
    }
}
