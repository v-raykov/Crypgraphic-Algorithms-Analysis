package com.dreamteam.algorithm.analysis.config.jackson.test.creator;

import com.dreamteam.algorithm.analysis.config.jackson.FieldNames;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmType;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.parameter.EncryptionParametersFactory;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.base.StreamCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import org.springframework.stereotype.Component;

import static com.dreamteam.algorithm.analysis.config.jackson.test.creator.CreatorUtilities.*;

@Getter
@Component
public class StreamCipherTestCreator implements TestCreator<StreamCipherEncryptionAlgorithm> {
    private final AlgorithmType type = AlgorithmType.STREAM_CIPHER_ENCRYPTION;

    @Override
    public Test create(JsonNode node, StreamCipherEncryptionAlgorithm algorithm) {
        String plaintext = node.get(FieldNames.DATA.toString()).asText();
        node = getObjectNodeIfProvided(node, FieldNames.PARAMETERS.toString());
        return new EncryptionTest<>(algorithm, plaintext,
                EncryptionParametersFactory.createStreamCipherEncryptionParameters(
                        algorithm,
                        getBytesIfProvided(node, FieldNames.ENCRYPTION_KEY.toString()),
                        getIntIfProvided(node, FieldNames.KEY_SIZE.toString()))
        );
    }
}
