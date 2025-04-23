package com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash;

import org.bouncycastle.crypto.digests.MD5Digest;
import org.springframework.stereotype.Component;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash.HashAlgorithm;

@Component
public class MD5 implements HashAlgorithm {

    @Override
    public byte[] hash(byte[] data) {
        // Create an instance of MD5Digest (from BouncyCastle)
        MD5Digest md5 = new MD5Digest();

        // Process the input data
        md5.update(data, 0, data.length);

        // Create an array to store the hash result (MD5 produces a 128-bit hash)
        byte[] hash = new byte[md5.getDigestSize()];

        // Finalize the hash
        md5.doFinal(hash, 0);

        return hash;
    }
}
