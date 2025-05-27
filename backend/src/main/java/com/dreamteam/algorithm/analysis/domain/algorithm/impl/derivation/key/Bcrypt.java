package com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size.SingleFixedKeySize;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base.CountBasedKeyDerivationAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.parameter.CountBasedKeyDerivationParameters;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.crypto.generators.BCrypt;
import org.springframework.stereotype.Component;

import static com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmUtilities.secureRandom;

@Component
@RequiredArgsConstructor
@Getter
public class Bcrypt extends CountBasedKeyDerivationAlgorithm implements SingleFixedKeySize {
    private final int keySize = 24;

    @Override
    public byte[] deriveKey(byte[] password, CountBasedKeyDerivationParameters parameters) {
        byte[] bcryptHash = BCrypt.generate(password, parameters.getSalt(), parameters.getIterations());
        int keySize = parameters.getKeySize();
        byte[] result = new byte[keySize];
        System.arraycopy(bcryptHash, 0, result, 0, Math.min(bcryptHash.length, keySize));
        return result;
    }

    @Override
    public boolean isValidSaltSize(int saltSize) {
        return saltSize == 16;
    }

    @Override
    public int getRandomSaltSize() {
        return 16;
    }

    @Override
    public boolean isValidIterations(int iterations) {
        return iterations >= 4 && iterations <= 16;
    }

    @Override
    public int getRandomIterations() {
        return secureRandom.nextInt(4, 16);
    }
}
