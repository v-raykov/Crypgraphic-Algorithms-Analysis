package com.dreamteam.algorithm.analysis.model.test;

import com.dreamteam.algorithm.analysis.config.jackson.test.TestDeserializer;
import com.dreamteam.algorithm.analysis.config.jackson.test.TestSerializer;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.Algorithm;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(using = TestDeserializer.class)
@JsonSerialize(using = TestSerializer.class)
public abstract class Test {
   public abstract Algorithm getAlgorithm();
}
