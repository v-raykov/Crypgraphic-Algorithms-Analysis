package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption;

import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.MultipleFixedKeySizes;
import com.dreamteam.algorithm.analysis.domain.algorithm.option.RequiresCBCEngine;
import com.dreamteam.algorithm.analysis.domain.algorithm.option.RequiresIv;
import lombok.Getter;
import lombok.Setter;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.SerpentEngine;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class Serpent implements EncryptionAlgorithm, RequiresIv, MultipleFixedKeySizes, RequiresCBCEngine {

    private final int ivSize = 16; // Serpent block size is 128 bits (16 bytes)
    private final List<Integer> keySizes = List.of(16, 24, 32);
    private final BlockCipher engine = new SerpentEngine();
    private byte[] iv;

    public Serpent() {
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
