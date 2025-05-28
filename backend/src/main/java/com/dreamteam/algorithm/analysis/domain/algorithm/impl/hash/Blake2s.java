package com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash;

import lombok.Getter;
import org.bouncycastle.crypto.digests.Blake2sDigest;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Blake2s extends HashAlgorithm {
    private final Blake2sDigest digest = new Blake2sDigest(256); // 32 bytes
}
