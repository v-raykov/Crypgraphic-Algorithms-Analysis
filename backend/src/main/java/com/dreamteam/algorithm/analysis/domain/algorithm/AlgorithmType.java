package com.dreamteam.algorithm.analysis.domain.algorithm;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.KeyDerivationAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.digital.signature.DigitalSignatureAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.hash.HashAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.homomorphic.HomomorphicEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.key.exchange.KeyExchangeAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.zero.knowedge.ZeroKnowledgeProofAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AlgorithmType {
    KEY_DERIVATION("Key Derivation"),
    DIGITAL_SIGNATURE("Digital Signature"),
    ENCRYPTION("Encryption"),
    HASHING("Hashing"),
    HOMOMORPHIC_ENCRYPTION("Homomorphic Encryption"),
    KEY_EXCHANGE("Key Exchange"),
    ZERO_KNOWLEDGE_PROOF("Zero-Knowledge Proof");

    final String stringValue;

    @Override
    public String toString() {
        return stringValue;
    }

    public static AlgorithmType fromAlgorithm(Algorithm algorithm) {
        return switch (algorithm) {
            case KeyDerivationAlgorithm ignored -> KEY_DERIVATION;
            case DigitalSignatureAlgorithm ignored -> DIGITAL_SIGNATURE;
            case EncryptionAlgorithm ignored -> ENCRYPTION;
            case HashAlgorithm ignored -> HASHING;
            case HomomorphicEncryptionAlgorithm ignored -> HOMOMORPHIC_ENCRYPTION;
            case KeyExchangeAlgorithm ignored -> KEY_EXCHANGE;
            case ZeroKnowledgeProofAlgorithm ignored -> ZERO_KNOWLEDGE_PROOF;
            default -> throw new IllegalStateException("Unexpected value: " + algorithm);
        };
    }
}
