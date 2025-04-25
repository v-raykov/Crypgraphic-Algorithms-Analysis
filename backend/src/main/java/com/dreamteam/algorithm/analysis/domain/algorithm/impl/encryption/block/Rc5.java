package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.block;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.BlockCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size.VaryingKeySizes;
import lombok.Getter;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.RC532Engine;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Rc5 extends BlockCipherEncryptionAlgorithm implements VaryingKeySizes {
    private final int minKeySize = 5;
    private final int maxKeySize = 255;

    private final BlockCipher engine = new RC532Engine();

    private final int ivSize = 8;
}

