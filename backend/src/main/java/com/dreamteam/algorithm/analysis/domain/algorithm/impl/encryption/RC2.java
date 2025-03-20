package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption;

import com.dreamteam.algorithm.analysis.domain.algorithm.key.sizes.VaryingKeySizes;
import com.dreamteam.algorithm.analysis.domain.algorithm.option.RequiresIv;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.RC2Engine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;

@Component
public class RC2 implements EncryptionAlgorithm, RequiresIv, VaryingKeySizes {

    private final int ivSize = 8; // RC2 block size is 64 bits (8 bytes)
    private byte[] iv;

    public RC2() {
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

    private byte[] processData(boolean encrypt, byte[] data, byte[] key) throws Exception {
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Invalid key size for RC2");
        }

        BlockCipher engine = new RC2Engine();
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(engine));
        cipher.init(encrypt, new ParametersWithIV(new KeyParameter(key), iv));

        byte[] output = new byte[cipher.getOutputSize(data.length)];
        int bytesProcessed = cipher.processBytes(data, 0, data.length, output, 0);
        bytesProcessed += cipher.doFinal(output, bytesProcessed);

        return Arrays.copyOf(output, bytesProcessed);
    }

    @Override
    public int getIvSize() {
        return ivSize;
    }

    @Override
    public byte[] getIv() {
        return iv;
    }

    @Override
    public void setIv(byte[] iv) {
        if (iv == null || iv.length != ivSize) {
            throw new IllegalArgumentException("Invalid IV size for RC2");
        }
        this.iv = iv;
    }

    @Override
    public byte[] generateRandomIv() {
        byte[] randomIv = new byte[ivSize];
        new Random().nextBytes(randomIv);
        return randomIv;
    }

    public boolean isValidKey(byte[] key) {
        return key.length >= 1 && key.length <= 16; // Key sizes in bytes (1–16 bytes)
    }
}