package com.dreamteam.algorithm.analysis.config.jackson;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionParametersFactory;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.block.BlockCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.stream.StreamCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.dreamteam.algorithm.analysis.web.service.algorithm.AlgorithmService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@RequiredArgsConstructor
public class TestFactory {
    private final AlgorithmService algorithmService;

    public Test createTestFromJson(JsonNode node) {
        var algorithm = algorithmService.getAlgorithmByName(node.get("algorithm").asText());
        return switch (algorithm) {
            case EncryptionAlgorithm<?> a -> createEncryptionTest(node, a);
            default -> throw new IllegalStateException("Unexpected value: " + algorithm);
        };
    }

    private Test createEncryptionTest(JsonNode node, EncryptionAlgorithm<?> algorithm) {
        var plaintext = node.get("plaintext").asText();
        var key = getBytesIfProvided(node, "encryptionKey");
        var keySize = getIntIfProvided(node, "encryptionKeySize");
        if (algorithm instanceof BlockCipherEncryptionAlgorithm a) {
            var parameters = EncryptionParametersFactory.createBlockCipherEncryptionParameters(a, key, keySize, getBytesIfProvided(node, "iv"));
            return new EncryptionTest<>(a, plaintext, parameters);
        }
        if (algorithm instanceof StreamCipherEncryptionAlgorithm a) {
            var parameters = EncryptionParametersFactory.createStreamCipherEncryptionParameters(a, key, keySize);
            return new EncryptionTest<>(a, plaintext, parameters);
        }
        throw new IllegalStateException();
    }

    private int getIntIfProvided(JsonNode node, String fieldName) {
        if (node.has("parameters")) {
            node = node.get("parameters");
            if (node.has(fieldName)) {
                return node.get(fieldName).asInt();
            }
        }
        return 0;
    }

    private byte[] getBytesIfProvided(JsonNode node, String fieldName) {
        if (node.has("parameters")){
            node = node.get("parameters");
            if (node.has(fieldName)) {
                return Base64.getDecoder().decode(node.get(fieldName).asText());
            }
        }
        return null;
    }
}
