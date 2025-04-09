package com.dreamteam.algorithm.analysis.web.service.algorithm.result.storage;

import com.dreamteam.algorithm.analysis.config.exception.not.found.TestResultNotFoundException;
import com.dreamteam.algorithm.analysis.model.test.TestResult;
import com.dreamteam.algorithm.analysis.repository.TestResultRepository;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Service
@RequiredArgsConstructor
@RequestScope
public class SessionResultStorage implements ResultStorage {
    @Getter
    private final TestResultRepository resultRepository;
    private final HttpSession httpSession;

    @Override
    public TestResult addResult(TestResult result) {
        getResults().add(result);
        return result;
    }

    @Override
    public List<TestResult> getResults() {
        var sessionData = (SessionData) httpSession.getAttribute("sessionData");
        if (sessionData == null) {
            httpSession.setAttribute("sessionData", new SessionData());
            return getResults();
        }
        return sessionData.getTestResults();
    }

    @Override
    public TestResult getTestResultById(String id) {
        return getResults().stream()
                .filter(result -> result.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TestResultNotFoundException(id));
    }
}
