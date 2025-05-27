package com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash;

import lombok.Getter;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Md5 extends HashAlgorithm {
    private final MD5Digest digest = new MD5Digest();
}
