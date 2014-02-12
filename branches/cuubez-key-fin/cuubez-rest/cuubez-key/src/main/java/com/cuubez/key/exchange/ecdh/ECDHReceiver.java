package com.cuubez.key.exchange.ecdh;

import java.security.KeyPair;

import javax.crypto.KeyAgreement;

import com.cuubez.key.exchange.IDHReceiver;

public class ECDHReceiver implements IDHReceiver {

	ECDHKeyAgreement rAgreement;
	KeyAgreement rAgree;
	KeyPair rPair;

	public byte[] receiverSharedSecret(byte[] rawPublicKey) {
		try {

			rAgree.doPhase(
					rAgreement.readPublicKey("originator", rawPublicKey), true);

			// generate the key bytes
			byte[] bSharedSecret = rAgree.generateSecret();

			return bSharedSecret;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public byte[] generateKeyAgreement() {
		try {
			rAgreement = new ECDHKeyAgreement();
			rAgree = (KeyAgreement) rAgreement.generateKeyInitializer();
			rPair = (KeyPair) rAgreement.getKeyPair();

			// two party agreement
			rAgree.init(rPair.getPrivate());

			return rAgreement.writePublicKey(rPair, "receiver");

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}