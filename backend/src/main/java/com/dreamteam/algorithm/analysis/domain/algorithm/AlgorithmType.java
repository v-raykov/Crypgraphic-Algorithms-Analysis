package com.dreamteam.algorithm.analysis.domain.algorithm;

import com.dreamteam.algorithm.analysis.config.exception.not.found.AlgorithmTypeNotFoundException;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.KeyDerivationAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.digital.signature.DigitalSignatureAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.block.BlockCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash.HashAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.homomorphic.HomomorphicEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.key.exchange.KeyExchangeAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.zero.knowedge.ZeroKnowledgeProofAlgorithm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public enum AlgorithmType {
    KEY_DERIVATION("Key Derivation", "key-derivation" ),
    DIGITAL_SIGNATURE("Digital Signature", "digital-signature"),
    ENCRYPTION("Encryption", "encryption"),
    HASHING("Hashing", "hashing"),
    HOMOMORPHIC_ENCRYPTION("Homomorphic Encryption", "homomorphic-encryption"),
    KEY_EXCHANGE("Key Exchange", "key-exchange"),
    ZERO_KNOWLEDGE_PROOF("Zero-Knowledge Proof", "zero-knowledge-proof");

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
            case KeyDerivationAlgorithm ignored -> KEY_DERIVATION;
            case DigitalSignatureAlgorithm ignored -> DIGITAL_SIGNATURE;
            case BlockCipherEncryptionAlgorithm ignored -> ENCRYPTION;
            case HashAlgorithm ignored -> HASHING;
            case HomomorphicEncryptionAlgorithm ignored -> HOMOMORPHIC_ENCRYPTION;
            case KeyExchangeAlgorithm ignored -> KEY_EXCHANGE;
            case ZeroKnowledgeProofAlgorithm ignored -> ZERO_KNOWLEDGE_PROOF;
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
