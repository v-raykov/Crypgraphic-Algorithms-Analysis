package com.dreamteam.algorithm.analysis.repository;

import com.dreamteam.algorithm.analysis.model.TestResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestResultRepository extends MongoRepository<TestResult, String> {
    Optional<TestResult> findByOwnerIdAndId(String ownerId, String id);

    List<TestResult> findByOwnerId(String id);
}
