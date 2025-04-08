package com.dreamteam.algorithm.analysis.web.controller;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.dreamteam.algorithm.analysis.model.test.TestResult;
import com.dreamteam.algorithm.analysis.web.service.algorithm.TestService;
import com.dreamteam.algorithm.analysis.web.service.algorithm.result.storage.ResultStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AlgorithmController {
    private final TestService testService;
    private final ResultStorage resultStorage;

    @GetMapping("/algorithm")
    public ResponseEntity<List<Algorithm>> getAlgorithms() {
        return ResponseEntity.ok()
                .body(testService.getAlgorithms());
    }

    @PostMapping("/test")
    public ResponseEntity<TestResult> testAlgorithm(@RequestBody Test test) {
        var result = testService.testAlgorithm(test, resultStorage);
        return ResponseEntity.created(URI.create("/test/" + result.getId()))
                .body(result);
    }

    @GetMapping("/test")
    public ResponseEntity<List<TestResult>> getTestResults() {
        return ResponseEntity.ok().body(resultStorage.getTestResults());
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<TestResult> getTestResultById(@PathVariable String id) {
        return ResponseEntity.ok()
                .body(resultStorage.getTestResultById(id));
    }
}
