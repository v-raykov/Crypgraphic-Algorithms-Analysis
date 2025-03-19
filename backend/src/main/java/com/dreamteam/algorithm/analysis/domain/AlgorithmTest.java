package com.dreamteam.algorithm.analysis.domain;

import com.dreamteam.algorithm.analysis.domain.algorithm.encryption.TwoFish;

import java.util.Arrays;

public class AlgorithmTest {

    public static void main(String[] args) {
        try {
            // Example 128-bit key (16 bytes)
            byte[] key = new byte[] {
                    (byte) 0x01, (byte) 0x23, (byte) 0x45, (byte) 0x67,
                    (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF,
                    (byte) 0xFE, (byte) 0xDC, (byte) 0xBA, (byte) 0x98,
                    (byte) 0x76, (byte) 0x54, (byte) 0x32, (byte) 0x10
            };

            // Message to encrypt ("hello world")
            String message = "hello world";
            System.out.println("Original message: " + message);

            // Pad the message to fit the block size
            byte[] plaintext = padMessage(message.getBytes(), TwoFish.getBlockSize());
            System.out.println("Padded plaintext: ");
            printByteArray(plaintext);

            // Initialize TwoFish with the key
            TwoFish twoFish = new TwoFish(key);

            // Encrypt the plaintext
            byte[] ciphertext = twoFish.encrypt(plaintext, key);
            System.out.println("Ciphertext (encrypted): ");
            printByteArray(ciphertext);

            // Decrypt the ciphertext
            byte[] decryptedText = twoFish.decrypt(ciphertext, key);
            System.out.println("Decrypted text (padded): ");
            printByteArray(decryptedText);

            // Remove padding from the decrypted text

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper method to pad the message to the required block size
    private static byte[] padMessage(byte[] message, int blockSize) {
        if (blockSize <= 0) {
            throw new IllegalArgumentException("Block size must be positive: " + blockSize);
        }
        int paddingLength = blockSize - (message.length % blockSize);
        byte[] paddedMessage = Arrays.copyOf(message, message.length + paddingLength);
        for (int i = message.length; i < paddedMessage.length; i++) {
            paddedMessage[i] = (byte) paddingLength; // Use PKCS7 padding
        }
        return paddedMessage;
    }

    // Helper method to remove padding from the message

    // Helper method to print byte arrays in hexadecimal format
    private static void printByteArray(byte[] array) {
        for (byte b : array) {
            System.out.print(String.format("%02X ", b));
        }
        System.out.println();
    }
}