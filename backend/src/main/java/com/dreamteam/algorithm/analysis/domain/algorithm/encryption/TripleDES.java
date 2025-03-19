package com.dreamteam.algorithm.analysis.domain.algorithm.encryption;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
@Component
public class TripleDES implements EncryptionAlgorithm {

    @Override
    public byte[] encrypt(byte[] data, byte[] key) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, "DESede");
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] key) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, "DESede");
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }

    public static SecretKey generateKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("DESede");
            keyGen.init(168);
            return keyGen.generateKey();
        } catch (Exception e) {
            throw new RuntimeException("Key generation failed", e);
        }
    }
}
