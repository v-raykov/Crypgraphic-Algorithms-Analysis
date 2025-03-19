package com.dreamteam.algorithm.analysis.domain.algorithm.impl.encryption;

import com.dreamteam.algorithm.analysis.domain.algorithm.key.sizes.MultipleFixedKeySizes;
import com.dreamteam.algorithm.analysis.domain.algorithm.option.RequiresIv;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.List;

@Component
@Getter
public class AdvancedEncryptionStandard implements EncryptionAlgorithm, RequiresIv, MultipleFixedKeySizes {

    private final List<Integer> keySizes = List.of(16, 24, 32);
    private final int ivSize = 16;
    @Setter
    private byte[] iv;

    public AdvancedEncryptionStandard() {
        iv = generateRandomIv();
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
        return cipher.doFinal(data);
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
        return cipher.doFinal(data);
    }
}
