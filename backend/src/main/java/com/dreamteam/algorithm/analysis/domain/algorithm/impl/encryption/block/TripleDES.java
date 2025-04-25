package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.block;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.BlockCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size.SingleFixedKeySize;
import lombok.Getter;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TripleDES extends BlockCipherEncryptionAlgorithm implements SingleFixedKeySize {
    private final int keySize = 24;

    private final int ivSize = 8;

    private final BlockCipher engine = new DESedeEngine();
}
