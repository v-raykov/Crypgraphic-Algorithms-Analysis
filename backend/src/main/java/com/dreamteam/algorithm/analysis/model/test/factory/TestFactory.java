package com.dreamteam.algorithm.analysis.model.test.factory;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionAlgorithm;
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
        var algorithm = algorithmService.findAlgorithm(node.get("algorithmName").asText());
        return switch (algorithm) {
            case EncryptionAlgorithm ignored -> createEncryptionTest(node);
            default -> throw new IllegalStateException("Unexpected value: " + algorithm);
        };
    }

    private Test createEncryptionTest(JsonNode node) {
        var test = new EncryptionTest();
        test.setAlgorithmName(node.get("algorithmName").asText());
        test.setPlaintext(node.get("plaintext").asText());
        test.setEncryptionKey(getBytesIfProvided(node, "encryptionKey"));
        test.setKeySize(getIntIfProvided(node, "keySize"));
        test.setIv(getBytesIfProvided(node, "iv"));
        return test;
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
