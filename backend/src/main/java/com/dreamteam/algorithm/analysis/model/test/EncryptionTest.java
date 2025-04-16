package com.dreamteam.algorithm.analysis.model.test;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionParameters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EncryptionTest<T extends EncryptionParameters> implements Test {
    private EncryptionAlgorithm<T> algorithm;

    private String plaintext;
    private T parameters;
}
