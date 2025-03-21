package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption;

import com.dreamteam.algorithm.analysis.domain.algorithm.key.size.SingleFixedKeySize;
import com.dreamteam.algorithm.analysis.domain.algorithm.option.RequiresIv;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@Getter
@Setter
@Component
public class DataEncryptionStandard implements EncryptionAlgorithm, SingleFixedKeySize, RequiresIv {
    private final int keySize = 8;
    private static final String ALGORITHM = "DES/CBC/PKCS5Padding";
    private final int ivSize = 8;
    private byte[] iv;

    public DataEncryptionStandard() {
        this.iv = generateRandomIv();
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] key) throws Exception {
        return performCipherOperation(data, key, Cipher.ENCRYPT_MODE);
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] key) throws Exception {
        return performCipherOperation(data, key, Cipher.DECRYPT_MODE);
    }

    @Override
    public boolean validateKey(byte[] key) {
        return keySize == key.length;
    }

    private Cipher initializeCipher(byte[] key, int mode) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec secretKey = new SecretKeySpec(key, "DES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(mode, secretKey, ivSpec);
        return cipher;
    }

    private byte[] performCipherOperation(byte[] data, byte[] key, int mode) throws Exception {
        Cipher cipher = initializeCipher(key, mode);
        return cipher.doFinal(data);
    }
}