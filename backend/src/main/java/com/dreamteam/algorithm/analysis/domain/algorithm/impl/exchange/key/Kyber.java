package com.dreamteam.algorithm.analysis.domain.algorithm.impl.exchange.key;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size.MultipleFixedKeySizes;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;

@Getter
@Component
public class Kyber implements KeyExchangeAlgorithm, MultipleFixedKeySizes {
    private final List<Integer> keySizes = List.of(64, 96, 128);
    private final SecureRandom random = new SecureRandom();

    @Override
    public byte[] generateKeyPair() {
        // Use the smallest supported size by default
        int size = keySizes.get(0);
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
            // Invert and XOR for a simple post-quantum mix
            secret[i] = (byte) (~(publicKey[i % publicKey.length] ^ privateKey[i % privateKey.length]));
        }
        return secret;
    }
}
