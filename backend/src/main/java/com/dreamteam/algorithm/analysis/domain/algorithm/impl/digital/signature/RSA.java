package com.dreamteam.algorithm.analysis.domain.algorithm.impl.digital.signature;

import lombok.Data;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.engines.RSABlindedEngine;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Data
public class RSA implements DigitalSignatureAlgorithm {

    private final int keySize = 2048; // example fixed size if needed

    @Override
    public byte[] sign(byte[] data, byte[] privateKey) throws CryptoException, IOException {
        RSAKeyParameters privKeyParams = (RSAKeyParameters) PrivateKeyFactory.createKey(privateKey);

        AsymmetricBlockCipher rsaEngine = new RSABlindedEngine();
        PSSSigner signer = new PSSSigner(rsaEngine, new SHA256Digest(), 32);
        signer.init(true, privKeyParams);
        signer.update(data, 0, data.length);
        return signer.generateSignature();
    }

    @Override
    public boolean verify(byte[] data, byte[] signature, byte[] publicKey) throws IOException {
        RSAKeyParameters pubKeyParams = (RSAKeyParameters) PublicKeyFactory.createKey(publicKey);

        AsymmetricBlockCipher rsaEngine = new RSABlindedEngine();
        PSSSigner signer = new PSSSigner(rsaEngine, new SHA256Digest(), 32);
        signer.init(false, pubKeyParams);
        signer.update(data, 0, data.length);
        return signer.verifySignature(signature);
    }


}
