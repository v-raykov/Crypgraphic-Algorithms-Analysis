package com.dreamteam.algorithm.analysis.config.jackson.test.creator;

import com.dreamteam.algorithm.analysis.config.jackson.FieldNames;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmType;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.parameter.EncryptionParametersFactory;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.BlockCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import org.springframework.stereotype.Component;

import static com.dreamteam.algorithm.analysis.config.jackson.test.creator.CreatorUtilities.*;

@Getter
@Component
public class BlockCipherTestCreator implements TestCreator<BlockCipherEncryptionAlgorithm> {
    private final AlgorithmType type = AlgorithmType.BLOCK_CIPHER_ENCRYPTION;

    @Override
    public Test create(JsonNode node, BlockCipherEncryptionAlgorithm algorithm) {
        var plaintext = node.get(FieldNames.PLAINTEXT.toString()).asText();
        node = getObjectNodeIfProvided(node, FieldNames.PARAMETERS.toString());
        return new EncryptionTest<>(algorithm, plaintext,
                EncryptionParametersFactory.createBlockCipherEncryptionParameters(
                        algorithm,
                        getBytesIfProvided(node, FieldNames.ENCRYPTION_KEY.toString()),
                        getIntIfProvided(node, FieldNames.KEY_SIZE.toString()), getBytesIfProvided(node, FieldNames.IV.toString()))
                );
    }
}
