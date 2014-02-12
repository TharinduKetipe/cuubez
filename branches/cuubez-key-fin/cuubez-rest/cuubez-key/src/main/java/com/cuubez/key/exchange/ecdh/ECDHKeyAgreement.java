package com.cuubez.key.exchange.ecdh;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.KeyAgreement;

import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;

import com.cuubez.key.connector.keyExchangeConnector;
import com.cuubez.key.exchange.IKeyAgreement;

@SuppressWarnings("unused")
public class ECDHKeyAgreement implements IKeyAgreement {

	private keyExchangeConnector fileWriter = new keyExchangeConnector();

	private ECNamedCurveParameterSpec dhKeySpec;

	public ECNamedCurveParameterSpec getDhKeySpec() {
		return dhKeySpec;
	}

	public void setDhKeySpec(ECNamedCurveParameterSpec dhKeySpec) {
		this.dhKeySpec = dhKeySpec;
	}

	public byte[] writePublicKey(KeyPair keyPair, String fileName) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		try {

			ECPublicKey publicKey = (ECPublicKey) KeyFactory.getInstance(
					"ECDH", "BC").generatePublic(
					new X509EncodedKeySpec(rawPublicKey));

			return publicKey;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object generateKeyInitializer() {
		// TODO Auto-generated method stub
		ECDHParameterInitializer ecdhParams = new ECDHParameterInitializer();
		ecdhParams.setCurveName(ECDHParameterInitializer.p256);
		ECNamedCurveParameterSpec ecdhKeySpec = (ECNamedCurveParameterSpec) ecdhParams
				.initialize();
		setDhKeySpec(ecdhKeySpec);
		KeyAgreement aKeyAgree = null;
		try {
			aKeyAgree = KeyAgreement.getInstance("ECDHC", "BC");
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
		// TODO Auto-generated method stub

		KeyPairGenerator keyGen = null;
		try {
			keyGen = KeyPairGenerator.getInstance("ECDH", "BC");
			keyGen.initialize(getDhKeySpec(), new SecureRandom());
			KeyPair pair = keyGen.generateKeyPair();
			return pair;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
