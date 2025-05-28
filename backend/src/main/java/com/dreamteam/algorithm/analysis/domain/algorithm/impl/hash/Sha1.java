package com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash;

import lombok.Getter;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Sha1 extends HashAlgorithm {
    private final SHA1Digest digest = new SHA1Digest();
}
