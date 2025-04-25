# Prerequisites
- **EACH** algorithm **MUST** be implemented using the **Bouncy Castle** library
- **EACH** algorithm **MUST** be implemented as an Object.
- The algorithm **MUST NOT be a STATIC** belonging to a class!!!
- **BouncyCastleProvider MUST NOT** be added to **Security**. This is handled elsewhere 
- **EACH** algorithm **MUST** be annotated with the **@Component** annotation
- **EACH** class **MUST NOT** implement **GET** and **SET** methods, instead they should be added as **LOMBOK ANNOTATIONS** just over the class name
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

### Key sizes (IN BYTES)
Regarding the size of the key/s that it uses
- MultipleFixedKeySizes 
  - The class implementing it **MUST** have a non-static field:
  - ``private final List<Integer> keySizes = List.of({})``
- SingleFixedKeySizes   
  - The class implementing it **MUST** have a non-static field:
  - ``private final int keySize={}``
- VaryingKeySizes       
  - The class implementing it **MUST** have non-static fields:
    - ``private final int minKeySize = {}``
    - ``private final int maxKeySize = {}``

# OPTIONAL
Each algorithm that is implemented using one of the following techniques **MUST** implement the interface that corresponds to it
- RequiresIv
  - `private final int ivSize = {}`
    - Algorithms that also require **BLOCK SIZE MUST NOT** have a different field, holding it, instead they should use the **ivSize non-static field**.
  - The constructor should be parameterless holding only
    - `iv = generateRandomIv()`
    - This method **MUST NOT** be implemented. It is already implemented in the interface. The classes should only call it.

- RequiresCBCEngine
  - `private final BlockCipher engine = {}`
  - encrypt and decrypt methods should simply call `processData(boolean, byte[] data, byte[] key)`
  - This method **MUST NOT** be implemented. It is already implemented in the interface. The classes should only call it.