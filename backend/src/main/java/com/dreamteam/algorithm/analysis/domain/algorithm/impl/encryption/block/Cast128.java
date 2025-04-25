package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.block;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.BlockCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size.VaryingKeySizes;
import lombok.Getter;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.CAST5Engine;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Cast128 extends BlockCipherEncryptionAlgorithm implements VaryingKeySizes {
    private final int minKeySize = 5;
    private final int maxKeySize = 16;

    private final int ivSize = 8;

    private final BlockCipher engine = new CAST5Engine();
}