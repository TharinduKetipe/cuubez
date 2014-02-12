package com.cuubez.key.exchange.ecdh;

import java.security.KeyPair;

import javax.crypto.KeyAgreement;

import com.cuubez.key.exchange.IDHOriginator;

public class ECDHOriginator implements IDHOriginator {

	ECDHKeyAgreement oAgreement;
	KeyAgreement oAgree;
	KeyPair oPair;

	public byte[] originatorSharedSecret(byte[] rawPublicKey) {
		try {

			oAgree.doPhase(oAgreement.readPublicKey("receiver", rawPublicKey),
					true);

			// generate the key bytes
			byte[] aSharedSecret = oAgree.generateSecret();

			// use this aShared as shared secret Key on originator side
			return aSharedSecret;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public byte[] generateKeyAgreement() {
		try {
			oAgreement = new ECDHKeyAgreement();
			oAgree = (KeyAgreement) oAgreement.generateKeyInitializer();
			oPair = (KeyPair) oAgreement.getKeyPair();

			// two party agreement
			oAgree.init(oPair.getPrivate());

			return oAgreement.writePublicKey(oPair, "originator");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
