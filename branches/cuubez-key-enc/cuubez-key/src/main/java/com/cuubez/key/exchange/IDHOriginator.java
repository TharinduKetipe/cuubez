package com.cuubez.key.exchange;

public interface IDHOriginator {
	//Object originatorSharedKey
	byte [] originatorSharedSecret(byte[] rawPublicKey);
	byte[] generateKeyAgreement();
}
