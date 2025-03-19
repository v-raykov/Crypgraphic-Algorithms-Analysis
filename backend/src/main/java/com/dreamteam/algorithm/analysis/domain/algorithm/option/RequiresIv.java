package com.dreamteam.algorithm.analysis.domain.algorithm.option;

import java.security.SecureRandom;

public interface RequiresIv {
    int getIvSize();
    byte[] getIv();
    void setIv(byte[] iv);
    default byte[] generateRandomIv() {
        byte[] iv = new byte[getIvSize()];
        new SecureRandom().nextBytes(iv);
        return iv;
    }
}

