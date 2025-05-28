package com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash;

import lombok.Getter;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Sha224 extends HashAlgorithm {
    private final SHA224Digest digest = new SHA224Digest();
}
