package com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash;

import com.dreamteam.algorithm.analysis.domain.algorithm.AlgorithmType;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.springframework.stereotype.Component;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash.HashAlgorithm;

import java.lang.reflect.Field;

@Component
public class MD4 implements HashAlgorithm {

    @Override
    public byte[] hash(byte[] data) {
        // Create an instance of MD4Digest (from BouncyCastle)
        MD4Digest md4 = new MD4Digest();

        // Process the input data
        md4.update(data, 0, data.length);

        // Create an array to store the hash result (MD4 produces a 128-bit hash)
        byte[] hash = new byte[md4.getDigestSize()];

        // Finalize the hash
        md4.doFinal(hash, 0);

        return hash;
    }

    @Override
    public AlgorithmType getType() {
        return null;
    }

    @Override
    public Field[] getFields() {
        return new Field[0];
    }
}
