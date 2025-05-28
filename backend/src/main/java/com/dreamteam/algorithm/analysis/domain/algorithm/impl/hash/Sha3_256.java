package com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash;

import lombok.Getter;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Sha3_256 extends HashAlgorithm {
    private final SHA3Digest digest = new SHA3Digest(256);
}
