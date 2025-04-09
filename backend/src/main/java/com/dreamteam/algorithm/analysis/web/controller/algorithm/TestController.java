package com.dreamteam.algorithm.analysis.web.controller.algorithm;

import com.dreamteam.algorithm.analysis.model.dto.TestResultDto;
import com.dreamteam.algorithm.analysis.model.test.Test;
import com.dreamteam.algorithm.analysis.web.service.algorithm.TestService;
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

    @PostMapping
    public ResponseEntity<TestResultDto> testAlgorithm(@RequestBody Test test) {
        var result = testService.testAlgorithm(test);
        return ResponseEntity.created(URI.create("/test/" + result.getId()))
                .body(result);
    }

    @GetMapping
    public ResponseEntity<List<TestResultDto>> getTestResults() {
        return ResponseEntity.ok().body(testService.getTestResults());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestResultDto> getTestResultById(@PathVariable String id) {
        return ResponseEntity.ok()
                .body(testService.getTestResultById(id));
    }
}
