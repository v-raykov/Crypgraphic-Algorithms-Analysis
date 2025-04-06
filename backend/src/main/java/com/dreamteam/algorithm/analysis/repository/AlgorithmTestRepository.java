package com.dreamteam.algorithm.analysis.repository;

import com.dreamteam.algorithm.analysis.model.test.EncryptionTestResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AlgorithmTestRepository extends MongoRepository<EncryptionTestResult, UUID> {
}
