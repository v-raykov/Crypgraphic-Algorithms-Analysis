package com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash;

import lombok.Getter;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Ripemd160 extends HashAlgorithm {
    private final RIPEMD160Digest digest = new RIPEMD160Digest();
}
