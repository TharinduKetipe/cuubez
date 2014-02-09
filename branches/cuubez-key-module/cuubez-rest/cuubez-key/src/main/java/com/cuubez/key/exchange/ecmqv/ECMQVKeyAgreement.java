package com.cuubez.key.exchange.ecmqv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.spec.X509EncodedKeySpec;

import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;

import com.cuubez.key.Utils;
import com.cuubez.key.connector.keyExchangeConnector;
import com.cuubez.key.exchange.IKeyAgreement;



import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.EllipticCurve;

import javax.crypto.KeyAgreement;

import org.bouncycastle.jce.ECPointUtil;
import org.bouncycastle.jce.spec.MQVPrivateKeySpec;
import org.bouncycastle.jce.spec.MQVPublicKeySpec;
import org.bouncycastle.util.encoders.Hex;


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
				
				ECParameterSpec ecmvqKeySpec = (ECParameterSpec) ecmvqParams.initialize();
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
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public byte[] writePublicKey(KeyPair keyPair,String fileName) {
		// TODO Auto-generated method stub
		X509EncodedKeySpec  pubX509=null;
		try {
			
		 byte[]  pubEnc = keyPair.getPublic().getEncoded();
		 pubX509 = new X509EncodedKeySpec(pubEnc);
		 
		 //Dump Public Key before writing to file from ECDH Agreement                 
         //System.out.println(fileName+" Public Key before writing to file from ECMVQ Agreement "+Utils.toHex(pubEnc));
         
//		 fileWriter.writeToFile(pubX509.getEncoded(),fileName);
         
                         
		} catch (Exception e) {
			e.printStackTrace();
		}
        return pubX509.getEncoded();
	}

	public ECPublicKey readPublicKey(String fileName,byte[] rawPublicKey) {
		// TODO Auto-generated method stub
		try {
			
           
//			byte[] key = fileWriter.readFile(fileName);
			
				         
			ECPublicKey publicKey = (ECPublicKey) KeyFactory.getInstance("ECMQV", "BC").generatePublic(new X509EncodedKeySpec(rawPublicKey));
	         
			//Dump public key after reading from file
			//System.out.println(fileName + "public key after reading from ECDHAgreement "+Utils.toHex(publicKey.getEncoded()));
			
            return publicKey;
	        
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}

	
}
