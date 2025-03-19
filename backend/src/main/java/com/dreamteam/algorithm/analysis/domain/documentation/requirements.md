# Prerequisites
- **EACH** algorithm **MUST** be implemented using the **Bouncy Castle** library
- **EACH** algorithm **MUST** be implemented as an Object.
- The algorithm **MUST NOT be a STATIC** belonging to a class!!!
- **BouncyCastleProvider MUST NOT** be added to **Security**. This is handled elsewhere 
- **EACH** algorithm **MUST** be annotated with the **@Component** annotation
- **GET** and **SET** methods **MUST** be implemented using the corresponding **LOMBOK** annotations
- Criteria such as IV or Key size **MUST** be implemented as **NON-STATIC FINAL FIELDS** of the class.
- **ALL FIELDS AND METHODS MUST BE NON-STATIC**
- **ALL EXCEPTIONS SHOULD BE THROWN UP THE STACK TRACE AND NOT HANDLED**

# REQUIRED
**EACH** algorithm **MUST** implement **ONLY ONE** of each interface in **EACH** category
### Type
Regarding the type of the algorithm
- KeyDerivationAlgorithm
  - `byte[] deriveKey(char[] password, byte[] salt, int iterations, int keyLength);`
- DigitalSignatureAlgorithm
  - `byte[] sign(byte[] data, byte[] privateKey);`
  - `boolean verify(byte[] data, byte[] signature, byte[] publicKey);`
- EncryptionAlgorithm
  - `byte[] encrypt(byte[] data, byte[] key) throws Exception;`
  - `byte[] decrypt(byte[] data, byte[] key) throws Exception;`
- HashAlgorithm
  - `byte[] hash(byte[] data);`
- HomomorphicAlgorithm
  - `byte[] homomorphicOperation(byte[] encryptedData1, byte[] encryptedData2);`
- KeyExchangeAlgorithm
  - `byte[] generateKeyPair();`
  - `byte[] deriveSharedSecret(byte[] publicKey, byte[] privateKey);`
- ZeroKnowledgeProofAlgorithm
  - `byte[] generateProof(byte[] secret, byte[] publicInput);`
  - `boolean verifyProof(byte[] proof, byte[] publicInput);`

### Key sizes
Regarding the size of the key/s that it uses
- MultipleFixedKeySizes 
  - `List<Integer> getKeySizes();`
- SingleFixedKeySizes   
  - `int getKeySize();`
- VaryingKeySizes       
  - `boolean isValidKey(byte[] key);`

# OPTIONAL
Each algorithm that is implemented using one of the following techniques **MUST** implement the interface that corresponds to it
- RequiresIv
  - `int getIvSize()`
    - Algorithms that also require **BLOCK SIZE MUST NOT** have a different field, holding it, instead they should use the **ivSize non-static field**.
  - `byte[] getIv();`
  - `void setIv(byte[] iv)`
  - `default generateRandomIv()`
    - This method **MUST NOT** be overwritten in any child classes. It should be used to **SET** the IV inside the class **CONSTRUCTOR**
