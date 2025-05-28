package com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmType;
import org.bouncycastle.crypto.ExtendedDigest;

import java.lang.reflect.Field;

public abstract class HashAlgorithm implements Algorithm {
    public byte[] hash(byte[] data) {
        ExtendedDigest digest = getDigest();
        digest.update(data, 0, data.length);
        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        return hash;
    }

    @Override
    public AlgorithmType getType() {
        return AlgorithmType.HASH;
    }

    @Override
    public Field[] getFields() {
        return new Field[0];
    }

    protected abstract ExtendedDigest getDigest();
}
