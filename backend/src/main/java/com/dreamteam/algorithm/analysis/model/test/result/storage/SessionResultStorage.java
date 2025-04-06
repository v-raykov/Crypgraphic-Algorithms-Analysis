package com.dreamteam.algorithm.analysis.model.test.result.storage;

import com.dreamteam.algorithm.analysis.model.test.TestResult;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class SessionResultStorage implements ResultStorage {
    private final HttpSession httpSession;

    @Override
    public List<TestResult> getTestResults() {
        SessionData sessionData = (SessionData) httpSession.getAttribute("sessionData");
        if (sessionData != null) {
            return sessionData.getTestResults();
        }
        httpSession.setAttribute("sessionData", new SessionData());
        return ((SessionData) httpSession.getAttribute("sessionData")).getTestResults();
    }

}
