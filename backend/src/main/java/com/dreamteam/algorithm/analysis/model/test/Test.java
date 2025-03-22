package com.dreamteam.algorithm.analysis.model.test;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.MultipleFixedKeySizes;
import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.SingleFixedKeySize;
import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.VaryingKeySizes;
import com.dreamteam.algorithm.analysis.model.test.factory.TestDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.security.SecureRandom;

@JsonDeserialize(using = TestDeserializer.class)
public interface Test {
   String getAlgorithmName();
   void setAlgorithmName(String algorithmName);
   String getPlaintext();
   void setPlaintext(String plaintext);

    void setDefaultValues(Algorithm algorithm);

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
