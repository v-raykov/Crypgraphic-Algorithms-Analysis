package com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash.HashAlgorithm;
import lombok.Getter;
import org.bouncycastle.crypto.digests.TigerDigest;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Tiger extends HashAlgorithm {
    private final TigerDigest digest = new TigerDigest();
}
