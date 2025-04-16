package com.dreamteam.algorithm.analysis.config.jackson.test.creator;

import com.dreamteam.algorithm.analysis.config.jackson.FieldNames;
import com.dreamteam.algorithm.analysis.domain.algorithm.AlgorithmType;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionParametersFactory;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.block.BlockCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

@Component
public class BlockCipherTestCreator implements TestCreator<BlockCipherEncryptionAlgorithm> {
    @Override
    public Test create(JsonNode node, BlockCipherEncryptionAlgorithm algorithm) {
        var plaintext = node.get(FieldNames.PLAINTEXT.toString()).asText();
        node.path(FieldNames.PARAMETERS.toString());
        byte[] key = Utilities.getBytesIfProvided(node, FieldNames.ENCRYPTION_KEY.toString());
        int keySize = Utilities.getIntIfProvided(node, FieldNames.ENCRYPTION_KEY_SIZE.toString());
        byte[] iv = Utilities.getBytesIfProvided(node, FieldNames.IV.toString());

        var params = EncryptionParametersFactory.createBlockCipherEncryptionParameters(algorithm, key, keySize, iv);
        return new EncryptionTest<>(algorithm, plaintext, params);
    }

    @Override
    public AlgorithmType getType() {
        return AlgorithmType.BLOCK_CIPHER_ENCRYPTION;
    }
}
