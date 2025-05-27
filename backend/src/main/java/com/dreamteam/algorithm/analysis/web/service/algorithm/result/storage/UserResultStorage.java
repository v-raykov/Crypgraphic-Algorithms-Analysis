package com.dreamteam.algorithm.analysis.web.service.algorithm.result.storage;

import com.dreamteam.algorithm.analysis.config.exception.not.found.TestResultNotFoundException;
import com.dreamteam.algorithm.analysis.model.User;
import com.dreamteam.algorithm.analysis.model.TestResult;
import com.dreamteam.algorithm.analysis.repository.TestResultRepository;
import com.dreamteam.algorithm.analysis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Service
@RequiredArgsConstructor
@RequestScope
public class UserResultStorage implements ResultStorage {
    private final TestResultRepository resultRepository;
    private final UserRepository userRepository;
    @Setter
    private User user;

    @Override
    public TestResult addResult(TestResult result) {
        result.setOwnerId(user.getId());
        var savedResult = resultRepository.save(result);
        user.addTestResultId(savedResult.getId());
        userRepository.save(user);
        return savedResult;
    }

    @Override
    public List<TestResult> getResults() {
        return resultRepository.findByOwnerId(user.getId());
    }

    @Override
    public TestResult getResultById(String id) {
        return resultRepository.findByOwnerIdAndId(user.getId(), id)
                .orElseThrow(() -> new TestResultNotFoundException(id));
    }
}
