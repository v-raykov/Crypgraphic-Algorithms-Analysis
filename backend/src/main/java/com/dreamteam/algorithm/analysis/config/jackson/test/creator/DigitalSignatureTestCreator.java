package com.dreamteam.algorithm.analysis.config.jackson.test.creator;

import com.dreamteam.algorithm.analysis.config.jackson.FieldNames;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmType;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.digital.signature.DigitalSignatureAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.digital.signature.parameter.DigitalSignatureKeyPair;
import com.dreamteam.algorithm.analysis.model.test.DigitalSignatureTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import org.springframework.stereotype.Component;

import static com.dreamteam.algorithm.analysis.config.jackson.test.creator.CreatorUtilities.getBytesIfProvided;

@Getter
@Component
public class DigitalSignatureTestCreator implements TestCreator<DigitalSignatureAlgorithm> {
    private final AlgorithmType type =  AlgorithmType.DIGITAL_SIGNATURE;

    @Override
    public Test create(JsonNode node, DigitalSignatureAlgorithm algorithm) {
        var plaintext = node.get(FieldNames.DATA.toString()).asText();
        if (node.has(FieldNames.KEY_PAIR.toString())) {
            node = node.get(FieldNames.KEY_PAIR.toString());
            return new DigitalSignatureTest(algorithm, plaintext, new DigitalSignatureKeyPair(getBytesIfProvided(node, FieldNames.PUBLIC_KEY.toString()), getBytesIfProvided(node, FieldNames.PRIVATE_KEY.toString())));
        }
        return new DigitalSignatureTest(algorithm, plaintext, algorithm.generateKeyPair());
    }
}
