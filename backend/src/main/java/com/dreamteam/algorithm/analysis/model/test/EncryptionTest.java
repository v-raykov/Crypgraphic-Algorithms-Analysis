package com.dreamteam.algorithm.analysis.model.test;

import com.dreamteam.algorithm.analysis.config.exception.InvalidKeySizeException;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.option.RequiresIv;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EncryptionTest implements Test {
    private final EncryptionAlgorithm algorithm;
    private final String plaintext;
    private byte[] encryptionKey;
    private int keySize;

    private byte[] iv;
    private int ivSize;

    public EncryptionTest(EncryptionTest test) {
        this.algorithm = test.algorithm;
        this.plaintext = test.getPlaintext();
        this.encryptionKey = test.getEncryptionKey();
        this.keySize = test.getKeySize();
        this.iv = test.getIv();
        this.ivSize = test.getIvSize();
    }

    public EncryptionTest(EncryptionAlgorithm algorithm, String plaintext, byte[] encryptionKey, int keySize, byte[] iv) {
        this.algorithm = algorithm;
        this.plaintext = plaintext;
        this.encryptionKey = encryptionKey;
        this.keySize = keySize;
        if (algorithm instanceof RequiresIv a) {
            this.ivSize = a.getIvSize();
        }
        setDefaultValues();
    }

    public EncryptionTest(EncryptionAlgorithm algorithm, String plaintext) {
        this.algorithm = algorithm;
        this.plaintext = plaintext;
        setDefaultValues();
    }

    // Default constructor needed for inheriting class
    public EncryptionTest() {
        algorithm = null;
        plaintext = null;
    }

    @Override
    public void setDefaultValues() {
        if (encryptionKey == null) {
            setDefaultEncryptionKey();
        } else {
            keySize = encryptionKey.length;
        }
        if (algorithm instanceof RequiresIv a) {
            setDefaultIv(a);
        }
    }

    private void setDefaultIv(RequiresIv algorithm) {
        ivSize = algorithm.getIvSize();
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
