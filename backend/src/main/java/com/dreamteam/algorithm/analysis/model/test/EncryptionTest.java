package com.dreamteam.algorithm.analysis.model.test;

import com.dreamteam.algorithm.analysis.config.exception.InvalidKeySizeException;
import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.option.RequiresIv;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EncryptionTest implements Test {
    private String algorithmName;
    private String plaintext;
    private byte[] encryptionKey;
    private int keySize;

    private byte[] iv;
    private int ivSize;

    public EncryptionTest(EncryptionTest test) {
        this.algorithmName = test.getAlgorithmName();
        this.plaintext = test.getPlaintext();
        this.encryptionKey = test.getEncryptionKey();
        this.keySize = test.getKeySize();
        this.iv = test.getIv();
        this.ivSize = test.getIvSize();
    }

    @Override
    public void setDefaultValues(Algorithm algorithm) {
        if (encryptionKey == null) {
            setDefaultEncryptionKey((EncryptionAlgorithm) algorithm);
        } else {
            keySize = encryptionKey.length;
        }
        if (algorithm instanceof RequiresIv) {
            setDefaultIv((RequiresIv) algorithm);
        }
    }

    private void setDefaultIv(RequiresIv algorithm) {
        ivSize = algorithm.getIvSize();
        if (iv == null) {
            iv = algorithm.generateRandomIv();
        }
    }

    private void setDefaultEncryptionKey(EncryptionAlgorithm algorithm) {
        if (keySize == 0) {
            keySize = getKeySize(algorithm);
        } else if (!algorithm.isValidKeySize(keySize)) {
            throw new InvalidKeySizeException(algorithm.getName(), keySize);
        }
        encryptionKey = getKey(keySize);
    }
}
