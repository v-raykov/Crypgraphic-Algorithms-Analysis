package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.block;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.BlockCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size.MultipleFixedKeySizes;
import lombok.Getter;
import lombok.Setter;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.CamelliaEngine;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class Camellia extends BlockCipherEncryptionAlgorithm implements MultipleFixedKeySizes {
    private final List<Integer> keySizes = List.of(16, 24, 32);

    private final int ivSize = 16;

    private final BlockCipher engine = new CamelliaEngine();
}
