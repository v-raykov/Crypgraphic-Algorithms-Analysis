package com.dreamteam.algorithm.analysis.domain.algorithm.encryption;

import org.springframework.stereotype.Component;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
@Component
public class DES implements EncryptionAlgorithm {
    private static final String SECRET_KEY = "0123456789abcdef";

    private SecretKey getSecretKey() throws Exception {
        DESKeySpec keySpec = new DESKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        return keyFactory.generateSecret(keySpec);
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting", e);
        }
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting", e);
        }
    }

    public static void main(String[] args) {
        DES des = new DES();
        String originalString = "Hello, world!";

        byte[] encryptedBytes = des.encrypt(originalString.getBytes(StandardCharsets.UTF_8), null);
        String encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);

        byte[] decryptedBytes = des.decrypt(Base64.getDecoder().decode(encryptedString), null);
        String decryptedString = new String(decryptedBytes, StandardCharsets.UTF_8);

        System.out.println("Original: " + originalString);
        System.out.println("Encrypted: " + encryptedString);
        System.out.println("Decrypted: " + decryptedString);
    }
}


