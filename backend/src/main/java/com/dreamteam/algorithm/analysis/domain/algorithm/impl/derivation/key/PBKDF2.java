package com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key;

import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.VaryingKeySizes;
import lombok.Getter;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.springframework.stereotype.Component;

@Component
public class PBKDF2 implements KeyDerivationAlgorithm, VaryingKeySizes {

    @Getter
    private final int minKeySize = 16; // 128 bits
    @Getter
    private final int maxKeySize = 64; // 512 bits

    @Override
    public byte[] deriveKey(char[] password, byte[] salt, int iterations, int keyLength) {
        PKCS5S2ParametersGenerator generator = new PKCS5S2ParametersGenerator(new SHA256Digest());
        generator.init(PKCS5S2ParametersGenerator.PKCS5PasswordToUTF8Bytes(password), salt, iterations);
        return ((KeyParameter) generator.generateDerivedParameters(keyLength * 8)).getKey(); // keyLength in bytes -> bits
    }
}
