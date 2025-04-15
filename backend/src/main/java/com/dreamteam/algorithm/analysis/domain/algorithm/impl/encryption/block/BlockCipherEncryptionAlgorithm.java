package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.block;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

import java.util.Arrays;

import static com.dreamteam.algorithm.analysis.config.GlobalStaticConstants.secureRandom;

public abstract class BlockCipherEncryptionAlgorithm implements EncryptionAlgorithm<BlockCipherEncryptionParameters> {

    @Override
    public byte[] encrypt(byte[] data, BlockCipherEncryptionParameters parameters) throws Exception {
        return processData(true, data, parameters.getEncryptionKey(), parameters.getIv());
    }

    @Override
    public byte[] decrypt(byte[] data, BlockCipherEncryptionParameters parameters) throws Exception {
        return processData(false, data, parameters.getEncryptionKey(), parameters.getIv());
    }

    public byte[] generateRandomIv() {
        byte[] iv = new byte[getIvSize()];
        secureRandom.nextBytes(iv);
        return iv;
    }

    protected byte[] processData(boolean encrypt, byte[] data, byte[] key, byte[] iv) throws Exception {
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(CBCBlockCipher.newInstance(getEngine()));
        cipher.init(encrypt, new ParametersWithIV(new KeyParameter(key), iv));
        byte[] output = new byte[cipher.getOutputSize(data.length)];
        int bytesProcessed = cipher.processBytes(data, 0, data.length, output, 0);
        bytesProcessed += cipher.doFinal(output, bytesProcessed);
        return Arrays.copyOf(output, bytesProcessed);
    }

    protected abstract BlockCipher getEngine();

    public abstract int getIvSize();
}
