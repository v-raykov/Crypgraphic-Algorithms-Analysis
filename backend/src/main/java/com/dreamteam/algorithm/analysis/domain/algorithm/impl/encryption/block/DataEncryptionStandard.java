package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.block;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size.SingleFixedKeySize;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.BlockCipherEncryptionAlgorithm;
import lombok.Getter;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.DESEngine;
import org.springframework.stereotype.Component;

@Component
@Getter
public class DataEncryptionStandard extends BlockCipherEncryptionAlgorithm implements SingleFixedKeySize {
    private final int keySize = 8;

    private final int ivSize = 8;

    private final BlockCipher engine = new DESEngine();
}