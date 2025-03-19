package com.dreamteam.algorithm.analysis.web.service.algorithm;

import com.dreamteam.algorithm.analysis.domain.algorithm.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class KeyService {
    private final SecureRandom secureRandom = new SecureRandom();
/*
    public byte[] generateRandomKey(Algorithm algorithm) {
        var sizes = algorithm.getKeySizes();
        byte[] key;
        if (sizes.size() == 1) {
             key = new byte[sizes.getFirst()];
            secureRandom.nextBytes(key);
        } else {

        }

        return key;
    }
 */
}
