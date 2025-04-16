package com.dreamteam.algorithm.analysis.config.jackson.creator;

import com.dreamteam.algorithm.analysis.config.jackson.JsonUtils;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionParametersFactory;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.stream.StreamCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

@Component
public class StreamCipherTestCreator implements TestCreator<StreamCipherEncryptionAlgorithm> {
    @Override
    public Class<StreamCipherEncryptionAlgorithm> getSupportedClass() {
        return StreamCipherEncryptionAlgorithm.class;
    }

    @Override
    public Test create(JsonNode node, StreamCipherEncryptionAlgorithm algorithm) {
        var plaintext = node.get("plaintext").asText();
        byte[] key = JsonUtils.getBytesIfProvided(node, "encryptionKey");
        int keySize = JsonUtils.getIntIfProvided(node, "encryptionKeySize");
        var params = EncryptionParametersFactory.createStreamCipherEncryptionParameters(algorithm, key, keySize);
        return new EncryptionTest<>(algorithm, plaintext, params);
    }
}
