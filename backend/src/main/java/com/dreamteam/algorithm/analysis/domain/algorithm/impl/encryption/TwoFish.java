package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption;

import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.MultipleFixedKeySizes;
import com.dreamteam.algorithm.analysis.domain.algorithm.option.RequiresIv;
import lombok.Getter;
import lombok.Setter;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.TwofishEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Getter
@Component
public class TwoFish implements EncryptionAlgorithm, MultipleFixedKeySizes, RequiresIv {
    private final List<Integer> keySizes = List.of(16, 24, 32);

    private final int ivSize = 16;

    @Setter
    private byte[] iv;

    public TwoFish() {
        this.iv = generateRandomIv();
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] key) throws InvalidCipherTextException {
        return processCipher(true, data, key);
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] key) throws InvalidCipherTextException {
        return processCipher(false, data, key);
    }

    private byte[] processCipher(boolean encrypt, byte[] input, byte[] key) throws InvalidCipherTextException {
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(CBCBlockCipher.newInstance(new TwofishEngine()));
        cipher.init(encrypt, new ParametersWithIV(new KeyParameter(key), iv));
        byte[] output = new byte[cipher.getOutputSize(input.length)];
        int processedBytes = cipher.processBytes(input, 0, input.length, output, 0);
        int finalProcessedBytes = cipher.doFinal(output, processedBytes);
        return Arrays.copyOf(output, processedBytes + finalProcessedBytes);
    }

}
