package com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash;

import lombok.Getter;
import org.bouncycastle.crypto.digests.WhirlpoolDigest;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Whirlpool extends HashAlgorithm {
    private final WhirlpoolDigest digest = new WhirlpoolDigest();
}
