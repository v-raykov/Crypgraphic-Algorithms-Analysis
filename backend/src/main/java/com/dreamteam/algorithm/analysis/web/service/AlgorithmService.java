package com.dreamteam.algorithm.analysis.web.service;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AlgorithmService {
    private final Map<String, Algorithm> algorithms;
}
