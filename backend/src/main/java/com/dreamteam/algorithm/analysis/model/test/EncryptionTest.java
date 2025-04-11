package com.dreamteam.algorithm.analysis.model.test;

import com.dreamteam.algorithm.analysis.config.exception.InvalidKeySizeException;
import com.dreamteam.algorithm.analysis.domain.algorithm.AlgorithmType;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EncryptionTest implements Test {
    private EncryptionAlgorithm algorithm;
    private final String type = AlgorithmType.ENCRYPTION.toString();
    private String plaintext;
    private byte[] encryptionKey;
    private int keySize;

    private byte[] iv;
    private int ivSize;

    public EncryptionTest(EncryptionAlgorithm algorithm, String plaintext, byte[] encryptionKey, int keySize, byte[] iv) {
        this.algorithm = algorithm;
        this.plaintext = plaintext;
        this.encryptionKey = encryptionKey;
        this.keySize = keySize;
        this.iv = iv;
        this.ivSize = algorithm.getIvSize();
        setDefaultValues();
    }

    public EncryptionTest(EncryptionAlgorithm algorithm, String plaintext) {
        this.algorithm = algorithm;
        this.plaintext = plaintext;
        setDefaultValues();
    }

    @Override
    public void setDefaultValues() {
        if (encryptionKey == null) {
            setDefaultEncryptionKey();
        } else {
            keySize = encryptionKey.length;
        }
        if (iv == null) {
            iv = algorithm.generateRandomIv();
        }
    }

    private void setDefaultEncryptionKey() {
        if (keySize == 0) {
            keySize = getKeySize(algorithm);
        } else if (!algorithm.isValidKeySize(keySize)) {
            throw new InvalidKeySizeException(algorithm.getName(), keySize);
        }
        encryptionKey = getKey(keySize);
    }
}
