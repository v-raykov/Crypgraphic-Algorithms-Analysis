package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.stream;

import com.dreamteam.algorithm.analysis.domain.algorithm.AlgorithmType;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionParameters;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionParameters;
import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.VaryingKeySizes;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.engines.RC4Engine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class RC4 extends StreamCipherEncryptionAlgorithm implements VaryingKeySizes {

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
    @Override
    public boolean isValidKeySize(int keySize) {
        return keySize >= minKeySize && keySize <= maxKeySize;
    }

    @Override
    public AlgorithmType getType() {
        return null;
    }

    @Override
    public Field[] getFields() {
        return new Field[0];
    }

    @Override
    public int getMinKeySize() {
        return minKeySize;
    }

    @Override
    public int getMaxKeySize() {
        return maxKeySize;
    }
}
