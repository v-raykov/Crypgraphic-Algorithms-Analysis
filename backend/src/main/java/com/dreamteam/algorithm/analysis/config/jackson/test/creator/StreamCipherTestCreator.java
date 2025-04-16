package com.dreamteam.algorithm.analysis.config.jackson.test.creator;

import com.dreamteam.algorithm.analysis.config.jackson.FieldNames;
import com.dreamteam.algorithm.analysis.domain.algorithm.AlgorithmType;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionParametersFactory;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.stream.StreamCipherEncryptionAlgorithm;
import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

@Component
public class StreamCipherTestCreator implements TestCreator<StreamCipherEncryptionAlgorithm> {

    @Override
    public Test create(JsonNode node, StreamCipherEncryptionAlgorithm algorithm) {
        var plaintext = node.get(FieldNames.PLAINTEXT.toString()).asText();
        byte[] key = Utilities.getBytesIfProvided(node, FieldNames.ENCRYPTION_KEY.toString());
        int keySize = Utilities.getIntIfProvided(node, FieldNames.ENCRYPTION_KEY_SIZE.toString());
        var params = EncryptionParametersFactory.createStreamCipherEncryptionParameters(algorithm, key, keySize);
        return new EncryptionTest<>(algorithm, plaintext, params);
    }

    @Override
    public AlgorithmType getType() {
        return AlgorithmType.STREAM_CIPHER_ENCRYPTION;
    }
}
