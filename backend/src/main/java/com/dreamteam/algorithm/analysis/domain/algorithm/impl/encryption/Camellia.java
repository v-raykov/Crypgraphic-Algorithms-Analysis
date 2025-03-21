package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption;

import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.MultipleFixedKeySizes;
import com.dreamteam.algorithm.analysis.domain.algorithm.option.RequiresCBCEngine;
import com.dreamteam.algorithm.analysis.domain.algorithm.option.RequiresIv;
import lombok.Getter;
import lombok.Setter;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.CamelliaEngine;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class Camellia implements EncryptionAlgorithm, RequiresIv, MultipleFixedKeySizes, RequiresCBCEngine {
    private final List<Integer> keySizes = List.of(16, 24, 32);
    private final int ivSize = 16;
    private final BlockCipher engine = new CamelliaEngine();
    private byte[] iv;

    public Camellia() {
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

    @Override
    public boolean validateKey(byte[] key) {
        return keySizes.contains(key.length);
    }
}
