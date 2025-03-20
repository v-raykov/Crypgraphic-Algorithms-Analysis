package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption;

import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.VaryingKeySizes;
import com.dreamteam.algorithm.analysis.domain.algorithm.option.RequiresCBCEngine;
import com.dreamteam.algorithm.analysis.domain.algorithm.option.RequiresIv;
import lombok.Getter;
import lombok.Setter;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.CAST5Engine;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class CAST128 implements EncryptionAlgorithm, RequiresIv, VaryingKeySizes, RequiresCBCEngine {

    private final int ivSize = 8; // CAST-128 block size is 64 bits (8 bytes)
    private final int minKeySize = 5;
    private final int maxKeySize = 16;
    private final BlockCipher engine = new CAST5Engine();
    private byte[] iv;

    public CAST128() {
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
