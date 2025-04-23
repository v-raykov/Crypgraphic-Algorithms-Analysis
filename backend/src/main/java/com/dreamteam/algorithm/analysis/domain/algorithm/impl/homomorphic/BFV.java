package com.dreamteam.algorithm.analysis.domain.algorithm.impl.homomorphic;

import com.dreamteam.algorithm.analysis.domain.algorithm.AlgorithmType;
import org.springframework.stereotype.Component;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.homomorphic.HomomorphicEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.VaryingKeySizes;
import lombok.Data;

@Data
@Component
public class BFV implements HomomorphicEncryptionAlgorithm, VaryingKeySizes {

    private final int minKeySize = 256;
    private final int maxKeySize = 256;

    @Override
    public byte[] homomorphicOperation(byte[] encryptedData1, byte[] encryptedData2) {
        // Simulated BFV homomorphic operation (addition).
        byte[] result = new byte[Math.max(encryptedData1.length, encryptedData2.length)];
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) (encryptedData1[i % encryptedData1.length] + encryptedData2[i % encryptedData2.length]);
        }
        return result;
    }
}
