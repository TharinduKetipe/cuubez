package com.cuubez.key.exchange.ecmqv;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyAgreement;

import org.bouncycastle.jce.spec.MQVPrivateKeySpec;
import org.bouncycastle.jce.spec.MQVPublicKeySpec;

public class ECMQVOriginator {

	ECMQVKeyAgreement oAgreement;
	KeyAgreement oAgree;
	KeyPair o1Pair;
	KeyPair o2Pair;

	public byte[] originatorSharedSecret(byte[] rawPublicKey1,
			byte[] rawPublicKey2) {
		try {

			MQVPublicKeySpec mvqPublicKeySpec = new MQVPublicKeySpec(
					oAgreement.readPublicKey("receiverPub1", rawPublicKey1),
					oAgreement.readPublicKey("receiverPub2", rawPublicKey2));

			oAgree.doPhase(mvqPublicKeySpec, true);

			byte[] aSharedSecret = oAgree.generateSecret();

			return aSharedSecret;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public Map<String, byte[]> generateKeyAgreement() {

		Map<String, byte[]> keys = new HashMap<String, byte[]>();

		try {
			oAgreement = new ECMQVKeyAgreement();
			oAgree = (KeyAgreement) oAgreement.generateKeyInitializer();
			o1Pair = (KeyPair) oAgreement.getKeyPair();
			o2Pair = (KeyPair) oAgreement.getKeyPair();

			// Two party agreement
			oAgree.init(new MQVPrivateKeySpec(o1Pair.getPrivate(), o2Pair
					.getPrivate(), o2Pair.getPublic()));

			// dump private key
			// System.out.println("dump Originator private key on making "+Utils.toHex(oPair.getPrivate().getEncoded()));

			keys.put("key1",
					oAgreement.writePublicKey(o1Pair, "originatorPub1"));
			keys.put("key2",
					oAgreement.writePublicKey(o2Pair, "originatorPub2"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return keys;
	}
}
