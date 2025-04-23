package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.stream;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionParameters;
import lombok.Getter;

public class StreamCipherEncryptionParameters extends EncryptionParameters {
    public StreamCipherEncryptionParameters(byte[] key) {
        super(key);
    }
}
