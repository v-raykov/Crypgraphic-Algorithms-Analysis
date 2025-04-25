package com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmType;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class MD4 implements HashAlgorithm {

    @Override
    public byte[] hash(byte[] data) {
        MD4Digest md4 = new MD4Digest();
        md4.update(data, 0, data.length);
        byte[] hash = new byte[md4.getDigestSize()];
        md4.doFinal(hash, 0);

        return hash;
    }

    @Override
    public AlgorithmType getType() {
        return HashAlgorithm.super.getType();
    }

    @Override
    public Field[] getFields() {
        return new Field[0];
    }
}
