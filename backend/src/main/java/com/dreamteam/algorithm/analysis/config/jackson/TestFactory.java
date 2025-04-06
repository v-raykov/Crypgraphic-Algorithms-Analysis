package com.dreamteam.algorithm.analysis.config.jackson;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.dreamteam.algorithm.analysis.web.service.algorithm.TestingService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@RequiredArgsConstructor
public class TestFactory {
    private final TestingService testingService;

    public Test createTestFromJson(JsonNode node) {
        var algorithm = testingService.findAlgorithm(node.get("algorithm").asText());
        return switch (algorithm) {
            case EncryptionAlgorithm a -> createEncryptionTest(node, a);
            default -> throw new IllegalStateException("Unexpected value: " + algorithm);
        };
    }

    private Test createEncryptionTest(JsonNode node, EncryptionAlgorithm algorithm) {
        return new EncryptionTest(algorithm,
                node.get("plaintext").asText(),
                getBytesIfProvided(node, "encryptionKey"),
                getIntIfProvided(node, "keySize"),
                getBytesIfProvided(node, "iv")
        );
    }

    private int getIntIfProvided(JsonNode node, String fieldName) {
        if (node.has(fieldName)) {
            return node.get(fieldName).asInt();
        }
        return 0;
    }

    private byte[] getBytesIfProvided(JsonNode node, String fieldName) {
        if (node.has(fieldName)) {
            return Base64.getDecoder().decode(node.get(fieldName).asText());
        }
        return null;
    }
}
