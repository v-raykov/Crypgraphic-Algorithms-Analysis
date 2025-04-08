package com.dreamteam.algorithm.analysis.model.test;

import com.dreamteam.algorithm.analysis.config.jackson.TestDeserializer;
import com.dreamteam.algorithm.analysis.config.jackson.TestSerializer;
import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.MultipleFixedKeySizes;
import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.SingleFixedKeySize;
import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.VaryingKeySizes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.security.SecureRandom;

@JsonDeserialize(using = TestDeserializer.class)
@JsonSerialize(using = TestSerializer.class)
public interface Test {
   Algorithm getAlgorithm();
   String getPlaintext();

    void setDefaultValues();

    default byte[] getKey(int keySize) {
        var key = new byte[keySize];
        new SecureRandom().nextBytes(key);
        return key;
    }

    default int getKeySize(Algorithm algorithm) {
        return switch (algorithm) {
            case MultipleFixedKeySizes a -> a.getKeySizes().getFirst();
            case SingleFixedKeySize a -> a.getKeySize();
            case VaryingKeySizes a -> a.getRandomKeySize();
            default -> throw new IllegalStateException("Unexpected value: " + algorithm);
        };
    }
}
