package com.dreamteam.algorithm.analysis.domain.algorithm.impl.exchange.key;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size.VaryingKeySizes;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Getter
@Component
public class EllipticCurve implements KeyExchangeAlgorithm, VaryingKeySizes {
    private final int minKeySize = 20;
    private final int maxKeySize = 65;
    private final SecureRandom random = new SecureRandom();

    @Override
    public byte[] generateKeyPair() {
        // Use the minimum size for simplicity
        int size = minKeySize;
        byte[] privateKey = new byte[size];
        byte[] publicKey = new byte[size];
        random.nextBytes(privateKey);
        random.nextBytes(publicKey);
        // Concatenate public||private
        byte[] combined = new byte[2 * size];
        System.arraycopy(publicKey, 0, combined, 0, size);
        System.arraycopy(privateKey, 0, combined, size, size);
        return combined;
    }

    @Override
    public byte[] deriveSharedSecret(byte[] publicKey, byte[] privateKey) {
        int maxLen = Math.max(publicKey.length, privateKey.length);
        byte[] secret = new byte[maxLen];
        for (int i = 0; i < maxLen; i++) {
            // Simple mixing for ECC example
            secret[i] = (byte) ((publicKey[i % publicKey.length] ^ privateKey[i % privateKey.length]) ^ 0x5A);
        }
        return secret;
    }
}


