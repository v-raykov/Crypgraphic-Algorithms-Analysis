package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.block;

import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.MultipleFixedKeySizes;
import lombok.Getter;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.RC6Engine;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
public class Rc6 extends BlockCipherEncryptionAlgorithm implements MultipleFixedKeySizes {
    private final List<Integer> keySizes = List.of(16, 24, 32);
    private final int ivSize = 16;

    private final BlockCipher engine = new RC6Engine();

    @Override
    public boolean isValidKeySize(int keySize) {
        return keySizes.contains(keySize);
    }
}
