package com.dreamteam.algorithm.analysis.domain.algorithm.encryption;

import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
@Component
public class AES implements EncryptionAlgorithm {
    private static final String SECRET_KEY = "my_super_secret_key_ho_ho_ho";
    private static final String SALT = "ssshhhhhhhhhhh!!!!";
    private static final byte[] IV = new byte[16];

    private SecretKeySpec getSecretKey() throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), "AES");
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(), new IvParameterSpec(IV));
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting", e);
        }
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), new IvParameterSpec(IV));
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting", e);
        }
    }

    public static void main(String[] args) {
        AES aes = new AES();
        String originalString = "GeeksforGeeks";

        byte[] encryptedBytes = aes.encrypt(originalString.getBytes(StandardCharsets.UTF_8), null);
        String encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);

        byte[] decryptedBytes = aes.decrypt(Base64.getDecoder().decode(encryptedString), null);
        String decryptedString = new String(decryptedBytes, StandardCharsets.UTF_8);

        System.out.println("Original: " + originalString);
        System.out.println("Encrypted: " + encryptedString);
        System.out.println("Decrypted: " + decryptedString);
    }
}
