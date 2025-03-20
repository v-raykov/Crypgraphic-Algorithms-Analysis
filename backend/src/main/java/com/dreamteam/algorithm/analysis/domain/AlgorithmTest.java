package com.dreamteam.algorithm.analysis.domain;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.*;
import com.dreamteam.algorithm.analysis.domain.algorithm.key.sizes.MultipleFixedKeySizes;
import com.dreamteam.algorithm.analysis.domain.algorithm.key.sizes.SingleFixedKeySize;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

public class AlgorithmTest {
    private static final SecureRandom secureRandom = new SecureRandom();
    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        testEncryption(new AdvancedEncryptionStandard());
        testEncryption(new DataEncryptionStandard());
        testEncryption(new TwoFish());
        testEncryption(new TripleDES());
        testEncryption(new BlowFish());
        testEncryption(new Serpent());
        testEncryption(new Camellia());
        testEncryption(new CAST256());
        testEncryption(new RC6());
        testEncryption(new CAST128());

    }

    private static void testEncryption(EncryptionAlgorithm algorithm) throws Exception {
        String originalString = algorithm.getName() + " is being tested.";
        byte[] key = new byte[getKeySize(algorithm)];
        secureRandom.nextBytes(key);
        byte[] encrypted = algorithm.encrypt(originalString.getBytes(), key);
        byte[] decrypted = algorithm.decrypt(encrypted, key);

        System.out.println("Original: " + originalString);
        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));
        System.out.println("Decrypted: " + new String(decrypted, StandardCharsets.UTF_8));
        System.out.println();
    }

    private static int getKeySize(EncryptionAlgorithm algorithm) {
        return switch (algorithm) {
            case MultipleFixedKeySizes a -> a.getKeySizes().getFirst();
            case SingleFixedKeySize a -> a.getKeySize();
            default -> throw new IllegalStateException("Unexpected value: " + algorithm);
        };
    }
}
