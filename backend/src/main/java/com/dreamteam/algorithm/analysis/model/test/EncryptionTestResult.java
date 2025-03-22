package com.dreamteam.algorithm.analysis.model.test;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class EncryptionTestResult extends EncryptionTest implements TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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
