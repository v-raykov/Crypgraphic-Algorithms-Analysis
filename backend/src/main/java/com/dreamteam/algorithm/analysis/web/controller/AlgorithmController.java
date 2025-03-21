package com.dreamteam.algorithm.analysis.web.controller;

import com.dreamteam.algorithm.analysis.model.test.EncryptionTest;
import com.dreamteam.algorithm.analysis.model.test.TestResult;
import com.dreamteam.algorithm.analysis.web.service.algorithm.AlgorithmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AlgorithmController {
    private final AlgorithmService algorithmService;
    @PostMapping
    public TestResult testAlgorithm(@RequestBody EncryptionTest test) {

    }
}
