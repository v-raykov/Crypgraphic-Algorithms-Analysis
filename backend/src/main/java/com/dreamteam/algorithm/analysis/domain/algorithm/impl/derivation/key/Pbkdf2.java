package com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size.VaryingKeySizes;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base.CountBasedKeyDerivationAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.parameter.CountBasedKeyDerivationParameters;
import lombok.Getter;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Pbkdf2 extends CountBasedKeyDerivationAlgorithm implements VaryingKeySizes {
    private final int minKeySize = 16;
    private final int maxKeySize = 64;

    @Override
    public byte[] deriveKey(byte[] password, CountBasedKeyDerivationParameters parameters) {
        PKCS5S2ParametersGenerator generator = new PKCS5S2ParametersGenerator(new SHA256Digest());
        generator.init(password, parameters.getSalt(), parameters.getIterations());
        KeyParameter keyParameter = (KeyParameter) generator.generateDerivedParameters(parameters.getKeySize() * 8);
        return keyParameter.getKey();
    }
}
