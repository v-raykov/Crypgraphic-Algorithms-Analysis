package com.dreamteam.algorithm.analysis.model.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EncryptionTest extends Test {
    private int keySize;
    private String key;

    private int ivSize;
    private String iv;
}
