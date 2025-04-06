package com.dreamteam.algorithm.analysis.model.test.result.storage;

import com.dreamteam.algorithm.analysis.model.test.TestResult;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class SessionData {
    private final List<TestResult> testResults = new ArrayList<>();
}
