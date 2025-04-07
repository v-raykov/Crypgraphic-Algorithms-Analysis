package com.dreamteam.algorithm.analysis.web.service.algorithm.result.storage;

import com.dreamteam.algorithm.analysis.model.User;
import com.dreamteam.algorithm.analysis.model.test.TestResult;
import com.dreamteam.algorithm.analysis.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class UserResultStorage implements ResultStorage {
    private final UserRepository userRepository;
    private User user;

    @Override
    public void addTestResult(TestResult result) {
        user.getTestResults().add(result);
        userRepository.save(user);
    }

    @Override
    public List<TestResult> getTestResults() {
        return user.getTestResults();
    }
}
