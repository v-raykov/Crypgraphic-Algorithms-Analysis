package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.block;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.BlockCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size.VaryingKeySizes;
import lombok.Getter;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.BlowfishEngine;
import org.springframework.stereotype.Component;

@Component
@Getter
public class BlowFish extends BlockCipherEncryptionAlgorithm implements VaryingKeySizes {
    private final int minKeySize = 4;
    private final int maxKeySize = 56;

    private final int ivSize = 8;

    private final BlockCipher engine = new BlowfishEngine();
}
