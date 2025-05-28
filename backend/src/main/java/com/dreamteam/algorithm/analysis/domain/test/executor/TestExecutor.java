package com.dreamteam.algorithm.analysis.domain.test.executor;

import com.dreamteam.algorithm.analysis.model.TestResult;
import com.dreamteam.algorithm.analysis.model.test.Test;

public interface TestExecutor {
    <T extends Test> TestResult execute(T test);

    Class<? extends Test> supports();
}