package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption;

import com.dreamteam.algorithm.analysis.domain.algorithm.key.sizes.MultipleFixedKeySizes;
import com.dreamteam.algorithm.analysis.domain.algorithm.option.RequiresIv;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.CamelliaEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class Camellia implements EncryptionAlgorithm, RequiresIv, MultipleFixedKeySizes {

    private final int ivSize = 16; // Camellia block size is 128 bits (16 bytes)
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

    private byte[] processData(boolean encrypt, byte[] data, byte[] key) throws Exception {
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Invalid key size for Camellia");
        }

        BlockCipher engine = new CamelliaEngine();
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
            throw new IllegalArgumentException("Invalid IV size for Camellia");
        }
        this.iv = iv;
    }

    @Override
    public byte[] generateRandomIv() {
        byte[] randomIv = new byte[ivSize];
        new Random().nextBytes(randomIv);
        return randomIv;
    }

    @Override
    public List<Integer> getKeySizes() {
        return Arrays.asList(16, 24, 32); // Key sizes in bytes (128, 192, 256 bits)
    }


    public boolean isValidKey(byte[] key) {
        return getKeySizes().contains(key.length);
    }
}
