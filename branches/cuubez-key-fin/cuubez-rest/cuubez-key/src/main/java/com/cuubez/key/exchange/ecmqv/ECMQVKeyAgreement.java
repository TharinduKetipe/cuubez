package com.cuubez.key.exchange.ecmqv;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.ECParameterSpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.KeyAgreement;

import org.bouncycastle.jce.interfaces.ECPublicKey;

import com.cuubez.key.connector.keyExchangeConnector;
import com.cuubez.key.exchange.IKeyAgreement;

@SuppressWarnings("unused")
public class ECMQVKeyAgreement implements IKeyAgreement {

	private keyExchangeConnector fileWriter = new keyExchangeConnector();

	private ECParameterSpec MVQkeySpec;

	public ECParameterSpec getMVQkeySpec() {
		return MVQkeySpec;
	}

	public void setMVQkeySpec(ECParameterSpec MVQkeySpec) {
		this.MVQkeySpec = MVQkeySpec;
	}

	public Object generateKeyInitializer() {

		ECMQVParameterInitializer ecmvqParams = new ECMQVParameterInitializer();

		ECParameterSpec ecmvqKeySpec = (ECParameterSpec) ecmvqParams
				.initialize();
		setMVQkeySpec(ecmvqKeySpec);

		KeyAgreement aKeyAgree = null;

		try {
			aKeyAgree = KeyAgreement.getInstance("ECMQV", "BC");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aKeyAgree;
	}

	public Object getKeyPair() {

		KeyPairGenerator keyGen = null;
		try {
			keyGen = KeyPairGenerator.getInstance("ECMQV", "BC");
			keyGen.initialize(getMVQkeySpec(), new SecureRandom());
			KeyPair pair = keyGen.generateKeyPair();
			return pair;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public byte[] writePublicKey(KeyPair keyPair, String fileName) {

		X509EncodedKeySpec pubX509 = null;
		try {

			byte[] pubEnc = keyPair.getPublic().getEncoded();
			pubX509 = new X509EncodedKeySpec(pubEnc);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pubX509.getEncoded();
	}

	public ECPublicKey readPublicKey(String fileName, byte[] rawPublicKey) {

		try {

			ECPublicKey publicKey = (ECPublicKey) KeyFactory.getInstance(
					"ECMQV", "BC").generatePublic(
					new X509EncodedKeySpec(rawPublicKey));

			return publicKey;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
