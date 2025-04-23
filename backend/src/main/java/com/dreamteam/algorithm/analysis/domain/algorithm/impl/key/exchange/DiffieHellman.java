package com.dreamteam.algorithm.analysis.domain.algorithm.impl.key.exchange;

import org.springframework.stereotype.Component;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.key.exchange.KeyExchangeAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.MultipleFixedKeySizes;
import lombok.Data;

import java.security.SecureRandom;
import java.util.List;

@Data
@Component
public class DiffieHellman implements KeyExchangeAlgorithm, MultipleFixedKeySizes {
    private final List<Integer> keySizes = List.of(128, 256, 384, 512);
    private final SecureRandom random = new SecureRandom();

    @Override
    public byte[] generateKeyPair() {
        // Choose the smallest supported key size for this example
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
            secret[i] = (byte) (publicKey[i % publicKey.length] ^ privateKey[i % privateKey.length]);
        }
        return secret;
    }
}
