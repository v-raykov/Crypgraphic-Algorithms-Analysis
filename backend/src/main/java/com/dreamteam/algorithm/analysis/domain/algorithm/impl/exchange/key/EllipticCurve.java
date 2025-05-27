package com.dreamteam.algorithm.analysis.domain.algorithm.impl.exchange.key;

import com.dreamteam.algorithm.analysis.model.test.key.pair.AlgorithmKeyPair;
import com.dreamteam.algorithm.analysis.domain.algorithm.base.key.size.MultipleFixedKeySizes;
import lombok.Getter;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.agreement.ECDHBasicAgreement;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.springframework.stereotype.Component;

import java.security.spec.ECGenParameterSpec;
import java.util.List;

@Getter
@Component
public class EllipticCurve extends KeyExchangeAlgorithm implements MultipleFixedKeySizes {
    private final List<Integer> keySizes = List.of(192, 224, 256, 384, 521);

    @Override
    protected String getAlgorithm() {
        return "ECDH";
    }

    @Override
    public byte[] deriveSharedSecret(byte[] publicKeyBytes, byte[] privateKeyBytes) throws Exception {
        SubjectPublicKeyInfo pubInfo = SubjectPublicKeyInfo.getInstance(publicKeyBytes);
        ECPublicKeyParameters pubParams = (ECPublicKeyParameters) PublicKeyFactory.createKey(pubInfo);

        PrivateKeyInfo privInfo = PrivateKeyInfo.getInstance(privateKeyBytes);
        ECPrivateKeyParameters privParams = (ECPrivateKeyParameters) PrivateKeyFactory.createKey(privInfo);

        ECDHBasicAgreement agreement = new ECDHBasicAgreement();
        agreement.init(privParams);

        return agreement.calculateAgreement(pubParams).toByteArray();
    }

    @Override
    public AlgorithmKeyPair generateKeyPair(int keySize) {
        return new AlgorithmKeyPair(new ECGenParameterSpec(String.format("secp%dr1", keySize)));
    }
}
