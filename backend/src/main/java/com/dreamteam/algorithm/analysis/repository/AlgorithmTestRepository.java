package com.dreamteam.algorithm.analysis.repository;

import com.dreamteam.algorithm.analysis.model.AlgorithmTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlgorithmTestRepository extends JpaRepository<AlgorithmTest, UUID> {
}
