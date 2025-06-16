package com.dreamteam.algorithm.analysis.domain.algorithm.base;

import com.dreamteam.algorithm.analysis.config.exception.not.found.AlgorithmTypeNotFoundException;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base.CountBasedKeyDerivationAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base.ResourceBasedKeyDerivationAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.digital.signature.DigitalSignatureAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.BlockCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.StreamCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.exchange.key.KeyExchangeAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash.HashAlgorithm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public enum AlgorithmType {
    COUNT_BASED_KEY_DERIVATION("Count Based Key Derivation", "count-based-key-derivation"),
    RESOURCE_BASED_KEY_DERIVATION("Resource Based Key Derivation", "resource-based-key-derivation"),
    DIGITAL_SIGNATURE("Digital Signature", "digital-signature"),
    BLOCK_CIPHER_ENCRYPTION("Block Cipher Encryption", "block-encryption"),
    STREAM_CIPHER_ENCRYPTION("Stream Cipher Encryption", "stream-encryption"),
    HASH("Hashing", "hashing"),
    KEY_EXCHANGE("Key Exchange", "key-exchange");
    final String stringValue;
    @Getter
    final String endpoint;

    private static final Map<String, AlgorithmType> endpointMap = new HashMap<>();

    static {
        for (AlgorithmType type : values()) {
            endpointMap.put(type.endpoint, type);
        }
    }

    @Override
    public String toString() {
        return stringValue;
    }

    public static AlgorithmType fromAlgorithm(Algorithm algorithm) {
        return switch (algorithm) {
            case CountBasedKeyDerivationAlgorithm ignored -> COUNT_BASED_KEY_DERIVATION;
            case ResourceBasedKeyDerivationAlgorithm ignored -> RESOURCE_BASED_KEY_DERIVATION;
            case DigitalSignatureAlgorithm ignored -> DIGITAL_SIGNATURE;
            case BlockCipherEncryptionAlgorithm ignored -> BLOCK_CIPHER_ENCRYPTION;
            case StreamCipherEncryptionAlgorithm ignored -> STREAM_CIPHER_ENCRYPTION;
            case HashAlgorithm ignored -> HASH;
            case KeyExchangeAlgorithm ignored -> KEY_EXCHANGE;
            default -> throw new IllegalStateException("Unexpected value: " + algorithm);
        };
    }

    public static AlgorithmType fromEndpoint(String endpoint) {
        AlgorithmType type = endpointMap.get(endpoint);
        if (type == null) {
            throw new AlgorithmTypeNotFoundException(endpoint);
        }
        return type;
    }
}
