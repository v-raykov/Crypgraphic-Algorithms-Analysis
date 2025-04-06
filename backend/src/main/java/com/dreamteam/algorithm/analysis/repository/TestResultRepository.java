package com.dreamteam.algorithm.analysis.repository;

import com.dreamteam.algorithm.analysis.model.test.TestResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepository extends MongoRepository<TestResult, String> {
}
