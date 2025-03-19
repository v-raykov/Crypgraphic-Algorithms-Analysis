package com.dreamteam.algorithm.analysis.domain.algorithm.option;

public interface RequiresSalt {
    byte[] getSalt();
    void setSalt(byte[] salt);
    byte[] generateRandomSalt();
}
