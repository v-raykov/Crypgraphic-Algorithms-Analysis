package com.dreamteam.algorithm.analysis.model.test;

import com.dreamteam.algorithm.analysis.model.dto.TestResultDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Document
public class TestResult {
    @Id
    private String id;

    private String ownerId;

    private Test test;

    private String cipherText;

    private long cipherTime;
    private long decipherTime;
    private long cipherMemory;
    private long decipherMemory;

    private double entropy;
    private double frequencyScore;

    private LocalDateTime timestamp;

    public TestResult(Test test) {
        this.test = test;
    }

    public TestResultDto toDto() {
        return new TestResultDto(this);
    }
}
