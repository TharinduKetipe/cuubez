package com.cuubez.key.exchange;

public interface IDHReceiver {
	byte[] receiverSharedSecret(byte[] rawPublicKey);

	byte[] generateKeyAgreement();

}
