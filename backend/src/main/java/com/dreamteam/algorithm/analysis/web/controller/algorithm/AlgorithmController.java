package com.dreamteam.algorithm.analysis.web.controller.algorithm;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.AlgorithmType;
import com.dreamteam.algorithm.analysis.model.dto.AlgorithmDto;
import com.dreamteam.algorithm.analysis.web.service.algorithm.AlgorithmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/algorithm")
@RequiredArgsConstructor
public class AlgorithmController {
    private final AlgorithmService algorithmService;

    @GetMapping
    public ResponseEntity<List<AlgorithmDto>> getAlgorithms() {
        return ResponseEntity.ok()
                .body(algorithmService.getAlgorithms());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<AlgorithmDto> getAlgorithm(@PathVariable String name) {
        return ResponseEntity.ok()
                .body(algorithmService.getAlgorithmDtoByName(name));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<AlgorithmDto>> getAlgorithmsByType(@PathVariable String type) {
        return ResponseEntity.ok()
                .body(algorithmService.getAlgorithmsByType(AlgorithmType.fromEndpoint(type)));
    }
}
