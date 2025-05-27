package com.dreamteam.algorithm.analysis.domain.algorithm.impl.exchange.key;

import com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size.MultipleFixedKeySizes;
import lombok.Getter;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.agreement.DHBasicAgreement;
import org.bouncycastle.crypto.params.DHPrivateKeyParameters;
import org.bouncycastle.crypto.params.DHPublicKeyParameters;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
public class DiffieHellman extends KeyExchangeAlgorithm implements MultipleFixedKeySizes {
    private final List<Integer> keySizes = List.of(64, 72, 80, 88, 96, 104, 112, 120, 128, 256, 384);

    @Override
    protected String getAlgorithm() {
        return "DH";
    }

    @Override
    public byte[] deriveSharedSecret(byte[] publicKeyBytes, byte[] privateKeyBytes) throws Exception {
        SubjectPublicKeyInfo pubInfo = SubjectPublicKeyInfo.getInstance(publicKeyBytes);
        DHPublicKeyParameters pubParams = (DHPublicKeyParameters) PublicKeyFactory.createKey(pubInfo);

        PrivateKeyInfo privInfo = PrivateKeyInfo.getInstance(privateKeyBytes);
        DHPrivateKeyParameters privParams = (DHPrivateKeyParameters) PrivateKeyFactory.createKey(privInfo);

        DHBasicAgreement agreement = new DHBasicAgreement();
        agreement.init(privParams);

        return agreement.calculateAgreement(pubParams).toByteArray();
    }
}
