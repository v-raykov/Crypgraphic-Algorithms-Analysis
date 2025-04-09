package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption;

import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.SingleFixedKeySize;
import com.dreamteam.algorithm.analysis.domain.algorithm.option.RequiresIv;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

import java.util.Arrays;

@Component
@Getter
@Setter
public class TripleDES implements EncryptionAlgorithm, SingleFixedKeySize, RequiresIv {
    private final int keySize = 24;
    private final int ivSize = 8;
    private byte[] iv;

    public TripleDES() {
        iv = generateRandomIv();
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] key) throws InvalidCipherTextException {
        return processCipher(true, data, key);
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] key) throws InvalidCipherTextException {
        return processCipher(false, data, key);
    }

    @Override
    public boolean isValidKeySize(int keySize) {
        return keySize == keySize;
    }


    private byte[] processCipher(boolean encrypt, byte[] data, byte[] key) throws InvalidCipherTextException {
        BlockCipher engine = new DESedeEngine(); // Triple DES engine
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(CBCBlockCipher.newInstance(engine));
        cipher.init(encrypt, new ParametersWithIV(new KeyParameter(key), iv));

        byte[] output = new byte[cipher.getOutputSize(data.length)];
        int length = cipher.processBytes(data, 0, data.length, output, 0);
        length += cipher.doFinal(output, length);

        return Arrays.copyOf(output, length);
    }
}
