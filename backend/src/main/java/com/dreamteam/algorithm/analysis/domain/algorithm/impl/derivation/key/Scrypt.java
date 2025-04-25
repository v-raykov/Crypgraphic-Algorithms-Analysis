package com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size.VaryingKeySizes;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base.ResourceBasedKeyDerivationAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.parameter.ResourceBasedKeyDerivationParameters;
import lombok.Getter;
import org.bouncycastle.crypto.generators.SCrypt;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Scrypt extends ResourceBasedKeyDerivationAlgorithm implements VaryingKeySizes {
    private final int minKeySize = 16;
    private final int maxKeySize = 64;

    @Override
    public byte[] deriveKey(byte[] password, ResourceBasedKeyDerivationParameters parameters) {
        return SCrypt.generate(
                new String(password).getBytes(),
                parameters.getSalt(),
                (int) Math.pow(2, parameters.getCostFactor()),
                parameters.getBlockSize(),
                parameters.getParallelization(),
                parameters.getKeySize()
        );
    }
}
