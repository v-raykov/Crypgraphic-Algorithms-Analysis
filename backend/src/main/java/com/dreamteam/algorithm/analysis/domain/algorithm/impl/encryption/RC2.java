package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption;

import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.VaryingKeySizes;
import com.dreamteam.algorithm.analysis.domain.algorithm.option.RequiresCBCEngine;
import com.dreamteam.algorithm.analysis.domain.algorithm.option.RequiresIv;
import lombok.Getter;
import lombok.Setter;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.RC2Engine;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class RC2 implements EncryptionAlgorithm, RequiresIv, VaryingKeySizes, RequiresCBCEngine {
    private final int ivSize = 8; // RC2 block size is 64 bits (8 bytes)
    private final int minKeySize = 1;
    private final int maxKeySize = 16;
    private final BlockCipher engine = new RC2Engine();
    private byte[] iv;

    public RC2() {
        this.iv = generateRandomIv();
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] key) throws Exception {
        return processData(true, data, key);
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] key) throws Exception {
        return processData(false, data, key);
    }
}