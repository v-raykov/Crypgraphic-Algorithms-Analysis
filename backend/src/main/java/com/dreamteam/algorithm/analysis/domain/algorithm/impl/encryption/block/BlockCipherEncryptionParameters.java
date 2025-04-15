package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.block;

import com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption.EncryptionParameters;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@JsonPropertyOrder({"encryptionKey", "encryptionKeySize","iv", "ivSize"})
@Getter
public class BlockCipherEncryptionParameters extends EncryptionParameters {
    private final byte[] iv;
    private final int ivSize;

    public BlockCipherEncryptionParameters(byte[] key, byte[] iv) {
        super(key);
        this.iv = iv;
        ivSize = iv.length;
    }
}
