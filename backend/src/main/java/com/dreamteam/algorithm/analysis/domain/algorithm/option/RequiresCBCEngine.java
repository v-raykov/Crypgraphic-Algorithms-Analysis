package com.dreamteam.algorithm.analysis.domain.algorithm.option;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

import java.util.Arrays;

public interface RequiresCBCEngine extends RequiresIv {
    BlockCipher getEngine();
    default byte[] processData(boolean encrypt, byte[] data, byte[] key) throws Exception {
        var cipher = new PaddedBufferedBlockCipher(CBCBlockCipher.newInstance(getEngine()));
        cipher.init(encrypt, new ParametersWithIV(new KeyParameter(key), getIv()));
        var output = new byte[cipher.getOutputSize(data.length)];
        var bytesProcessed = cipher.processBytes(data, 0, data.length, output, 0);
        bytesProcessed += cipher.doFinal(output, bytesProcessed);
        return Arrays.copyOf(output, bytesProcessed);
    }
}
