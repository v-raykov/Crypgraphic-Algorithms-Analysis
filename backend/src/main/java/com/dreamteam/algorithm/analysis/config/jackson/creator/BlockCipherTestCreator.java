package com.dreamteam.algorithm.analysis.config.jackson.creator;

import com.dreamteam.algorithm.analysis.config.jackson.JsonUtils;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionParametersFactory;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.block.BlockCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

@Component
public class BlockCipherTestCreator implements TestCreator<BlockCipherEncryptionAlgorithm> {
    @Override
    public Class<BlockCipherEncryptionAlgorithm> getSupportedClass() {
        return BlockCipherEncryptionAlgorithm.class;
    }

    @Override
    public Test create(JsonNode node, BlockCipherEncryptionAlgorithm algorithm) {
        var plaintext = node.get("plaintext").asText();
        byte[] key = JsonUtils.getBytesIfProvided(node, "encryptionKey");
        int keySize = JsonUtils.getIntIfProvided(node, "encryptionKeySize");
        byte[] iv = JsonUtils.getBytesIfProvided(node, "iv");

        var params = EncryptionParametersFactory.createBlockCipherEncryptionParameters(algorithm, key, keySize, iv);
        return new EncryptionTest<>(algorithm, plaintext, params);
    }
}
