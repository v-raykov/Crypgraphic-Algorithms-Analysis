package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.stream;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmType;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size.VaryingKeySizes;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.StreamCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.parameter.StreamCipherEncryptionParameters;
import lombok.Getter;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.engines.RC4Engine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Rc4 extends StreamCipherEncryptionAlgorithm implements VaryingKeySizes {
    private final AlgorithmType type = AlgorithmType.STREAM_CIPHER_ENCRYPTION;

    private final int minKeySize = 5;     // Minimum key size (40 bits)
    private final int maxKeySize = 256;   // Maximum key size (2048 bits)

    @Override
    public byte[] encrypt(byte[] data, StreamCipherEncryptionParameters parameters) throws Exception {
        return process(true, data, parameters.getEncryptionKey());
    }

    @Override
    public byte[] decrypt(byte[] data, StreamCipherEncryptionParameters parameters) throws Exception {
        return process(false, data, parameters.getEncryptionKey());
    }

    private byte[] process(boolean forEncryption, byte[] data, byte[] key) {
        StreamCipher engine = new RC4Engine();
        engine.init(forEncryption, new KeyParameter(key));
        byte[] output = new byte[data.length];
        engine.processBytes(data, 0, data.length, output, 0);
        return output;
    }
}
