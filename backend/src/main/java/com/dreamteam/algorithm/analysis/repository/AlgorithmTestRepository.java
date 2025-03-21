package com.dreamteam.algorithm.analysis.repository;

import com.dreamteam.algorithm.analysis.model.test.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlgorithmTestRepository extends JpaRepository<TestResult, UUID> {
}
