package com.dreamteam.algorithm.analysis.domain.algorithm.option;

import static com.dreamteam.algorithm.analysis.config.GlobalStaticConstants.secureRandom;

public interface RequiresIv {
    int getIvSize();
    byte[] getIv();
    void setIv(byte[] iv);
    default byte[] generateRandomIv() {
        byte[] iv = new byte[getIvSize()];
        secureRandom.nextBytes(iv);
        return iv;
    }
}

