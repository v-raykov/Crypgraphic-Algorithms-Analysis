package com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash;

import lombok.Getter;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Sha384 extends HashAlgorithm {
    private final SHA384Digest digest = new SHA384Digest();
}
