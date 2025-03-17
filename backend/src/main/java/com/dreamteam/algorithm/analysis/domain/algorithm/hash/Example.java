package com.dreamteam.algorithm.analysis.domain.algorithm.hash;

import org.springframework.stereotype.Component;

@Component
public class Example implements HashAlgorithm {
    @Override
    public byte[] hash(byte[] data) {
        return new byte[0];
    }
}
