# Cryptographic Algorithms

## 1. Symmetric-Key Algorithms (Secret Key Cryptography - SKC)
These use the same key for both encryption and decryption.

### Block Ciphers
- **AES (Advanced Encryption Standard)**
- **DES (Data Encryption Standard)**
- **3DES (Triple DES)**
- **Blowfish**
- **Twofish**
- **Serpent**
- **Camellia**
- **CAST-128 / CAST-256**
- **RC2, RC5, RC6**
- **IDEA (International Data Encryption Algorithm)**

### Stream Ciphers
- **RC4**
- **ChaCha20**
- **Salsa20**
- **Rabbit**

---

## 2. Asymmetric-Key Algorithms (Public Key Cryptography - PKC)
These use a pair of keys (public & private) for encryption and decryption.

- **RSA (Rivest-Shamir-Adleman)**
- **DSA (Digital Signature Algorithm)**
- **Diffie-Hellman (DH) Key Exchange**
- **Elliptic Curve Cryptography (ECC)**
    - **ECDSA (Elliptic Curve Digital Signature Algorithm)**
    - **ECDH (Elliptic Curve Diffie-Hellman)**
    - **EdDSA (Edwards-curve Digital Signature Algorithm, including Ed25519 and Ed448)**
- **ElGamal**
- **McEliece Cryptosystem** (based on error-correcting codes)

---

## 3. Hashing Algorithms (Cryptographic Hash Functions - CHF)
Used for integrity verification and digital signatures.

- **MD4, MD5 (Message Digest algorithms)** (Considered insecure)
- **SHA-1** (Insecure)
- **SHA-2 Family (Secure Hash Algorithm 2)**
    - **SHA-224**
    - **SHA-256**
    - **SHA-384**
    - **SHA-512**
- **SHA-3 Family (Keccak-based)**
    - **SHA3-224**
    - **SHA3-256**
    - **SHA3-384**
    - **SHA3-512**
- **BLAKE2, BLAKE3**
- **RIPEMD-160**
- **Whirlpool**
- **Tiger**

---

## 4. Key Derivation Functions (KDFs)
Used for generating strong cryptographic keys from passwords or other inputs.

- **PBKDF2 (Password-Based Key Derivation Function 2)**
- **scrypt**
- **Argon2 (Winner of the Password Hashing Competition - PHC)**
- **bcrypt**

---

## 5. Digital Signature Algorithms
Provide authentication and integrity.

- **RSA Signature (RSA-PSS, RSA-PKCS#1 v1.5)**
- **DSA (Digital Signature Algorithm)**
- **ECDSA (Elliptic Curve DSA)**
- **EdDSA (Ed25519, Ed448)**
- **Schnorr Signatures** (Used in Bitcoin Taproot)

---

## 6. Authenticated Encryption Algorithms (AEAD - Authenticated Encryption with Associated Data)
Provide both encryption and authentication.

- **AES-GCM (Galois/Counter Mode)**
- **AES-CCM (Counter with CBC-MAC Mode)**
- **ChaCha20-Poly1305**
- **OCB (Offset Codebook Mode)**
- **EAX Mode**

---

## 7. Post-Quantum Cryptography (PQC)
Cryptographic algorithms designed to resist quantum attacks.

- **CRYSTALS-Kyber** (Public Key Encryption)
- **CRYSTALS-Dilithium** (Digital Signatures)
- **Falcon** (Digital Signatures)
- **NTRUEncrypt**
- **SPHINCS+ (Stateless Hash-Based Signatures)**
- **Rainbow (Multivariate-based Signatures)**

---

## 8. Homomorphic Encryption Algorithms
Enable computation on encrypted data.

- **Paillier Cryptosystem**
- **BFV (Brakerski/Fan-Vercauteren)**
- **BGV (Brakerski-Gentry-Vaikuntanathan)**
- **CKKS (Cheon-Kim-Kim-Song)**

---

## 9. Zero-Knowledge Proofs (ZKPs)
Enable proving knowledge without revealing information.

- **zk-SNARKs (Zero-Knowledge Succinct Non-Interactive Argument of Knowledge)**
- **zk-STARKs (Zero-Knowledge Scalable Transparent Argument of Knowledge)**

---

## 10. Lightweight Cryptography
Designed for constrained environments (IoT, embedded systems).

- **Speck & Simon (NSA-designed)**
- **PRESENT**
- **GIFT**
- **XTEA (eXtended Tiny Encryption Algorithm)**  
