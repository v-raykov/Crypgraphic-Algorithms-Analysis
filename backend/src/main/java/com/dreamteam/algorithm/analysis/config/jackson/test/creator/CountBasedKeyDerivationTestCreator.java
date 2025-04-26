package com.dreamteam.algorithm.analysis.config.jackson.test.creator;

import com.dreamteam.algorithm.analysis.config.jackson.FieldNames;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmType;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.base.CountBasedKeyDerivationAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.derivation.key.parameter.KeyDerivationParametersFactory;
import com.dreamteam.algorithm.analysis.model.test.KeyDerivationTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import org.springframework.stereotype.Component;

import static com.dreamteam.algorithm.analysis.config.jackson.test.creator.CreatorUtilities.*;

@Getter
@Component
public class CountBasedKeyDerivationTestCreator implements TestCreator<CountBasedKeyDerivationAlgorithm> {
    private final AlgorithmType type = AlgorithmType.COUNT_BASED_KEY_DERIVATION;

    @Override
    public Test create(JsonNode node, CountBasedKeyDerivationAlgorithm algorithm) {
        var plaintext = node.get(FieldNames.DATA.toString()).asText();
        node = getObjectNodeIfProvided(node, FieldNames.PARAMETERS.toString());
        return new KeyDerivationTest<>(algorithm, plaintext,
                KeyDerivationParametersFactory.createCountBasedKeyDerivationParameters(
                        algorithm,
                        getBytesIfProvided(node, FieldNames.SALT.toString()),
                        getIntIfProvided(node, FieldNames.KEY_SIZE.toString()),
                        getIntIfProvided(node, FieldNames.ITERATIONS.toString())));
    }
}
