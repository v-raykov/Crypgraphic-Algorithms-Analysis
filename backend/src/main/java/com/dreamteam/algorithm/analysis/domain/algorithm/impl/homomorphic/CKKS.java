package com.dreamteam.algorithm.analysis.domain.algorithm.impl.homomorphic;

import com.dreamteam.algorithm.analysis.domain.algorithm.AlgorithmType;
import org.springframework.stereotype.Component;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.homomorphic.HomomorphicEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.VaryingKeySizes;
import lombok.Data;

@Data
@Component
public class CKKS implements HomomorphicEncryptionAlgorithm, VaryingKeySizes {

    private final int minKeySize = 256;
    private final int maxKeySize = 256;

    @Override
    public byte[] homomorphicOperation(byte[] encryptedData1, byte[] encryptedData2) {
        // Simulated CKKS homomorphic operation (real number addition).
        float num1 = bytesToFloat(encryptedData1);
        float num2 = bytesToFloat(encryptedData2);
        float result = num1 + num2;
        return floatToBytes(result);
    }

    private float bytesToFloat(byte[] bytes) {
        int intBits = 0;
        for (int i = 0; i < 4; i++) {
            intBits |= (bytes[i] & 0xFF) << (i * 8);
        }
        return Float.intBitsToFloat(intBits);
    }

    private byte[] floatToBytes(float value) {
        int intBits = Float.floatToIntBits(value);
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++) {
            bytes[i] = (byte) ((intBits >> (i * 8)) & 0xFF);
        }
        return bytes;
    }
}
