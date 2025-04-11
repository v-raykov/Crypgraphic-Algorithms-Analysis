package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption;

import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.VaryingKeySizes;
import lombok.Getter;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.RC2Engine;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Rc2 extends EncryptionAlgorithm implements VaryingKeySizes {
    private final int minKeySize = 1;
    private final int maxKeySize = 16;
    private final int ivSize = 8;

    private final BlockCipher engine = new RC2Engine();
    @Override
    public boolean isValidKeySize(int keySize) {
        return keySize >= minKeySize && keySize <= maxKeySize;
    }
}
