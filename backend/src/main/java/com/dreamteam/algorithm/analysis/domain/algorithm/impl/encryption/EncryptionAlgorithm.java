package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

import java.util.Arrays;

import static com.dreamteam.algorithm.analysis.config.GlobalStaticConstants.secureRandom;

public abstract class EncryptionAlgorithm implements Algorithm {
    public byte[] encrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        return processData(true, data, key, iv);
    }

    public byte[] decrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        return processData(false, data, key, iv);
    }

    public byte[] generateRandomIv() {
        byte[] iv = new byte[getIvSize()];
        secureRandom.nextBytes(iv);
        return iv;
    }

    public abstract boolean isValidKeySize(int keySize);

    protected byte[] processData(boolean encrypt, byte[] data, byte[] key, byte[] iv) throws Exception {
        var cipher = new PaddedBufferedBlockCipher(CBCBlockCipher.newInstance(getEngine()));
        cipher.init(encrypt, new ParametersWithIV(new KeyParameter(key), iv));
        var output = new byte[cipher.getOutputSize(data.length)];
        var bytesProcessed = cipher.processBytes(data, 0, data.length, output, 0);
        bytesProcessed += cipher.doFinal(output, bytesProcessed);
        return Arrays.copyOf(output, bytesProcessed);
    }

    protected abstract BlockCipher getEngine();

    public abstract int getIvSize();
}
