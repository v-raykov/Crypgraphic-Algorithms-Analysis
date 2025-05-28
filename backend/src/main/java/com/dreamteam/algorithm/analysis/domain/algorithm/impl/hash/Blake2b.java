package com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash;

import lombok.Getter;
import org.bouncycastle.crypto.digests.Blake2bDigest;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Blake2b extends HashAlgorithm {
    private final Blake2bDigest digest = new Blake2bDigest(512); // 64 bytes
}
