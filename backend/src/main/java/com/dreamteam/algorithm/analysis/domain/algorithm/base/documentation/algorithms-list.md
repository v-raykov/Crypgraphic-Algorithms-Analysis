# Cryptographic Algorithms

## 1. Symmetric-Key Algorithms (Secret Key Cryptography - SKC)
These use the **same** key for both encryption and decryption.

### Block Ciphers
- **AES (Advanced Encryption Standard)** – `16, 24, 32 bytes` ✔
- **DES (Data Encryption Standard)** – `8 bytes` ✔
- **3DES (Triple DES)** – `24 bytes` ✔
- **Blowfish** – `4–56 bytes` ✔
- **Twofish** – `16, 24, 32 bytes` ✔
- **Serpent** – `16, 24, 32 bytes` ✔
- **Camellia** – `16, 24, 32 bytes` ✔
- **CAST-128** – `5–16 bytes` ✔
- **CAST-256** – `16, 24, 32 bytes` ✔
- **RC2** – `1–16 bytes` ✔
- **RC5** – `5–255 bytes` ✔
- **RC6** – `16, 24, 32 bytes` ✔
- **IDEA (International Data Encryption Algorithm)** – `16 bytes`

### Stream Ciphers
- **
- RC4** – `5–256 bytes`
- **ChaCha20** – `32 bytes`
- **Salsa20** – `32 bytes`
- **Rabbit** – `16 bytes`

---

## 2. Asymmetric-Key Algorithms (Public Key Cryptography - PKC)
These use a **pair of keys** (public & private) for encryption and decryption.

- **RSA (Rivest-Shamir-Adleman)** – `128, 256, 384, 512 bytes` (1024, 2048, 3072, 4096 bits)
- **DSA (Digital Signature Algorithm)** – `128, 256, 384 bytes` (1024, 2048, 3072 bits)
- **Diffie-Hellman (DH) Key Exchange** – `128, 256, 384, 512 bytes` (1024, 2048, 3072, 4096 bits)
- **Elliptic Curve Cryptography (ECC)** – `20–65 bytes`
  - **ECDSA (Elliptic Curve Digital Signature Algorithm)** – `28, 32, 48, 65 bytes`
  - **ECDH (Elliptic Curve Diffie-Hellman)** – `28, 32, 48, 65 bytes`
  - **EdDSA (Ed25519, Ed448)** – `32 bytes (Ed25519), 57 bytes (Ed448)`
- **ElGamal** – `≥256 bytes` (depends on security level)
- **McEliece Cryptosystem** – `≥576 bytes` (depends on security parameters)

---

## 3. Hashing Algorithms (Cryptographic Hash Functions - CHF)
Used for **integrity verification** and **digital signatures**.

- **MD4, MD5** – `16 bytes` (128-bit hash output)
- **SHA-1** – `20 bytes` (160-bit hash output)
- **SHA-2 Family (Secure Hash Algorithm 2)**
  - **SHA-224** – `28 bytes`
  - **SHA-256** – `32 bytes`
  - **SHA-384** – `48 bytes`
  - **SHA-512** – `64 bytes`
- **SHA-3 Family (Keccak-based)**
  - **SHA3-224** – `28 bytes`
  - **SHA3-256** – `32 bytes`
  - **SHA3-384** – `48 bytes`
  - **SHA3-512** – `64 bytes`
- **BLAKE2** – `32 or 64 bytes`
- **RIPEMD-160** – `20 bytes`
- **Whirlpool** – `64 bytes`
- **Tiger** – `24 bytes`

---

## 4. Key Derivation Functions (KDFs)
Used for generating **strong cryptographic keys** from passwords or other inputs.

- **PBKDF2 (Password-Based Key Derivation Function 2)** – `Variable key size`
- **scrypt** – `Variable key size`
- **Argon2** – `Variable key size`
- **bcrypt** – `24 bytes`

---

## 5. Digital Signature Algorithms
Provide **authentication** and **integrity**.

- **RSA Signature** – `128, 256, 384, 512 bytes`
- **DSA** – `128, 256, 384 bytes`
- **ECDSA** – `28, 32, 48, 65 bytes`
- **EdDSA (Ed25519, Ed448)** – `32 bytes (Ed25519), 57 bytes (Ed448)`
- **Schnorr Signatures** – `32 bytes` (Bitcoin Taproot uses 32-byte keys)

---

## 6. Authenticated Encryption Algorithms (AEAD - Authenticated Encryption with Associated Data)
Provide both **encryption** and **authentication**.

- **AES-GCM (Galois/Counter Mode)** – `16, 24, 32 bytes`
- **AES-CCM (Counter with CBC-MAC Mode)** – `16, 24, 32 bytes`
- **ChaCha20-Poly1305** – `32 bytes`
- **OCB, EAX** – `Follows the underlying cipher (e.g., AES: 16, 24, 32 bytes)`

---

## 7. Post-Quantum Cryptography (PQC)
Cryptographic algorithms designed to **resist quantum attacks**.

- **CRYSTALS-Kyber** – `64, 96, 128 bytes`
- **CRYSTALS-Dilithium** – `164, 244, 324 bytes`
- **Falcon** – `64, 128 bytes`
- **NTRUEncrypt** – `14, 16, 32 bytes`
- **SPHINCS+** – `16, 24, 32 bytes`
- **Rainbow** – `Variable (depends on security parameters)`

---

## 8. Homomorphic Encryption Algorithms
Enable **computation on encrypted data**.

- **Paillier** – `≥128 bytes`
- **BFV, BGV, CKKS** – `≥256 bytes`

---

## 9. Zero-Knowledge Proofs (ZKPs)
Enable proving **knowledge without revealing** information.

- **zk-SNARKs (Zero-Knowledge Succinct Non-Interactive Argument of Knowledge)** – `Variable (commonly ≥16 bytes security level)`
- **zk-STARKs (Zero-Knowledge Scalable Transparent Argument of Knowledge)** – `Variable (commonly ≥16 bytes security level)`

---

## 10. Lightweight Cryptography
Designed for **constrained environments** (IoT, embedded systems).

- **Speck & Simon (NSA-designed)** – `8, 12, 16 bytes`
- **PRESENT** – `10, 16 bytes`
- **GIFT** – `16 bytes`
- **XTEA (eXtended Tiny Encryption Algorithm)** – `16 bytes`
