package com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key;

import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.VaryingKeySizes;
import lombok.Getter;
import org.bouncycastle.crypto.generators.SCrypt;
import org.springframework.stereotype.Component;

@Component
public class scrypt implements KeyDerivationAlgorithm, VaryingKeySizes {

    @Getter
    private final int minKeySize = 16; // 128 bits
    @Getter
    private final int maxKeySize = 64; // 512 bits

    private final int N = 16384; // CPU/memory cost parameter (must be power of 2)
    private final int r = 8;     // Block size parameter
    private final int p = 1;     // Parallelization parameter

    @Override
    public byte[] deriveKey(char[] password, byte[] salt, int iterations, int keyLength) {
        byte[] passBytes = new String(password).getBytes(); // UTF-8 encoding assumed
        return SCrypt.generate(passBytes, salt, N, r, p, keyLength);
    }
}
