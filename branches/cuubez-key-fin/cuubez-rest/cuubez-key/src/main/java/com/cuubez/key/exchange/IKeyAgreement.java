package com.cuubez.key.exchange;

import java.security.KeyPair;

public interface IKeyAgreement {
	byte[] writePublicKey(KeyPair keyPair, String fileName);

	Object readPublicKey(String fileName, byte[] publicKey);

	Object generateKeyInitializer();

	Object getKeyPair();
}
