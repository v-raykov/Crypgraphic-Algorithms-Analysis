package com.dreamteam.algorithm.analysis.config.jackson.test.creator;

import com.dreamteam.algorithm.analysis.config.jackson.FieldNames;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmType;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.exchange.key.KeyExchangeAlgorithm;
import com.dreamteam.algorithm.analysis.domain.algorithm.impl.exchange.key.KeyExchangeKeyPairFactory;
import com.dreamteam.algorithm.analysis.model.test.KeyExchangeTest;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import org.springframework.stereotype.Component;

import static com.dreamteam.algorithm.analysis.config.jackson.test.creator.CreatorUtilities.getIntIfProvided;
import static com.dreamteam.algorithm.analysis.config.jackson.test.creator.CreatorUtilities.getObjectNodeIfProvided;

@Getter
@Component
public class KeyExchangeTestCreator implements TestCreator<KeyExchangeAlgorithm> {
    private final AlgorithmType type = AlgorithmType.KEY_EXCHANGE;

    @Override
    public Test create(JsonNode node, KeyExchangeAlgorithm algorithm) {
        return new KeyExchangeTest(algorithm,
                KeyExchangeKeyPairFactory.generateKeyPair(algorithm,
                        getIntIfProvided(
                                getObjectNodeIfProvided(node, FieldNames.PARAMETERS.toString()),
                                FieldNames.KEY_SIZE.toString())
                )
        );
    }
}
