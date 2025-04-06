package com.dreamteam.algorithm.analysis.model.test;

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
public class EncryptionTestResult extends EncryptionTest implements TestResult {
    @Id
    private String id;

    private String result;
    private long encryptionTime;
    private long decryptionTime;
    private long encryptionMemory;
    private long decryptionMemory;
    private LocalDateTime timestamp;

    public EncryptionTestResult(EncryptionTest test) {
        super(test);
    }
}
