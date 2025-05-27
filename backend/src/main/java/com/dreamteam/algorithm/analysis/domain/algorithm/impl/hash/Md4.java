package com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash;

import lombok.Getter;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Md4 extends HashAlgorithm {
    private final MD4Digest digest = new MD4Digest();
}
