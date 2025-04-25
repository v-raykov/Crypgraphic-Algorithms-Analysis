package com.dreamteam.algorithm.analysis.domain.algorithm.impl.homomorphic;

import org.springframework.stereotype.Component;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size.VaryingKeySizes;
import lombok.Data;

@Data
@Component
public class Paillier implements HomomorphicEncryptionAlgorithm, VaryingKeySizes {

    private final int minKeySize = 128;
    private final int maxKeySize = 128;

    @Override
    public byte[] homomorphicOperation(byte[] encryptedData1, byte[] encryptedData2) {
        // Simulated Paillier homomorphic operation (addition).
        byte[] result = new byte[Math.max(encryptedData1.length, encryptedData2.length)];
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) (encryptedData1[i % encryptedData1.length] + encryptedData2[i % encryptedData2.length]);
        }
        return result;
    }


}
