package com.dreamteam.algorithm.analysis.web.controller;

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
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    private final ResultStorage resultStorage;

    @PostMapping
    public ResponseEntity<TestResult> testAlgorithm(@RequestBody Test test) {
        var result = testService.testAlgorithm(test, resultStorage);
        return ResponseEntity.created(URI.create("/test/" + result.getId()))
                .body(result);
    }

    @GetMapping
    public ResponseEntity<List<TestResult>> getTestResults() {
        return ResponseEntity.ok().body(resultStorage.getTestResults());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestResult> getTestResultById(@PathVariable String id) {
        return ResponseEntity.ok()
                .body(resultStorage.getTestResultById(id));
    }
}
