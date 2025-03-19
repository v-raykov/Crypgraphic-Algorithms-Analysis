package com.dreamteam.algorithm.analysis.domain.algorithm.encryption;

import java.util.Arrays;

public class TwoFish implements EncryptionAlgorithm {

    // Constants for Twofish
    private static final int BLOCK_SIZE = 16; // 128-bit block size
    private static final int ROUNDS = 16; // Number of rounds
    private static final int SUBKEYS = 40; // Number of subkeys

    // Key and subkeys
    private int[] key;
    private int[] subKeys;

    // Constructor with key setup
    public TwoFish(byte[] key) {
        if (key.length != 16 && key.length != 24 && key.length != 32) {
            throw new IllegalArgumentException("Key must be 128, 192, or 256 bits long.");
        }
        this.key = new int[key.length / 4];
        for (int i = 0; i < this.key.length; i++) {
            this.key[i] = (key[i * 4] & 0xFF) |
                    ((key[i * 4 + 1] & 0xFF) << 8) |
                    ((key[i * 4 + 2] & 0xFF) << 16) |
                    ((key[i * 4 + 3] & 0xFF) << 24);
        }
        generateSubKeys();
    }

    // Getter for BLOCK_SIZE
    public static int getBlockSize() {
        return BLOCK_SIZE;
    }

    @Override
    public String getName() {
        return "Twofish (Simplified)";
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] keyBytes) {
        byte[] block = Arrays.copyOf(data, BLOCK_SIZE);
        return encryptBlock(block);
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] keyBytes) {
        if (data.length != BLOCK_SIZE) {
            throw new IllegalArgumentException("Data must be 128 bits long (16 bytes).");
        }
        byte[] block = Arrays.copyOf(data, BLOCK_SIZE);
        return decryptBlock(block);
    }

    // Generate subkeys (simplified)
    private void generateSubKeys() {
        subKeys = new int[SUBKEYS];
        for (int i = 0; i < SUBKEYS; i++) {
            subKeys[i] = i; // Placeholder, replace with real key schedule
        }
    }

    // Simplified block encryption
    private byte[] encryptBlock(byte[] plaintext) {
        int[] block = toIntArray(plaintext);
        for (int round = 0; round < ROUNDS; round++) {
            int temp = block[0];
            block[0] = block[1] ^ F(block[0], round);
            block[1] = block[2];
            block[2] = block[3];
            block[3] = temp;
        }
        return toByteArray(block);
    }

    // Simplified block decryption
    private byte[] decryptBlock(byte[] ciphertext) {
        int[] block = toIntArray(ciphertext);
        for (int round = ROUNDS - 1; round >= 0; round--) {
            int temp = block[3];
            block[3] = block[2];
            block[2] = block[1];
            block[1] = block[0] ^ F(block[3], round);
            block[0] = temp;
        }
        return toByteArray(block);
    }

    // Simplified F function
    private int F(int x, int round) {
        return x + subKeys[round];
    }

    // Helper to convert bytes to int[]
    private int[] toIntArray(byte[] input) {
        int[] out = new int[4];
        for (int i = 0; i < 4; i++) {
            out[i] = (input[i * 4] & 0xFF) |
                    ((input[i * 4 + 1] & 0xFF) << 8) |
                    ((input[i * 4 + 2] & 0xFF) << 16) |
                    ((input[i * 4 + 3] & 0xFF) << 24);
        }
        return out;
    }

    // Helper to convert int[] to bytes
    private byte[] toByteArray(int[] input) {
        byte[] out = new byte[BLOCK_SIZE];
        for (int i = 0; i < 4; i++) {
            out[i * 4] = (byte) (input[i] & 0xFF);
            out[i * 4 + 1] = (byte) ((input[i] >> 8) & 0xFF);
            out[i * 4 + 2] = (byte) ((input[i] >> 16) & 0xFF);
            out[i * 4 + 3] = (byte) ((input[i] >> 24) & 0xFF);
        }
        return out;
    }
}