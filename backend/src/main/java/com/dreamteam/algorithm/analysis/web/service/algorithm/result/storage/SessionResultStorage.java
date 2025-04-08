package com.dreamteam.algorithm.analysis.web.service.algorithm.result.storage;

import com.dreamteam.algorithm.analysis.model.test.TestResult;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Service
@RequiredArgsConstructor
@RequestScope
public class SessionResultStorage implements ResultStorage {
    private final HttpSession httpSession;

    @Override
    public void addTestResult(TestResult result) {
        getTestResults().add(result);
    }

    @Override
    public List<TestResult> getTestResults() {
        var sessionData = (SessionData) httpSession.getAttribute("sessionData");
        if (sessionData != null) {
            return sessionData.getTestResults();
        }
        httpSession.setAttribute("sessionData", new SessionData());
        return ((SessionData) httpSession.getAttribute("sessionData")).getTestResults();
    }

}
