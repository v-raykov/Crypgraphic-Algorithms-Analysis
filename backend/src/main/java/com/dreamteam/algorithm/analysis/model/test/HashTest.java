package com.dreamteam.algorithm.analysis.model.test;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash.HashAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HashTest extends Test {
    private HashAlgorithm algorithm;
    private String data;
}
