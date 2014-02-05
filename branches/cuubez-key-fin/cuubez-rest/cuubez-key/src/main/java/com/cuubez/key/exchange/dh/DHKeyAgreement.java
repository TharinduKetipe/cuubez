package com.cuubez.key.exchange.dh;


import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

import com.cuubez.key.Utils;
import com.cuubez.key.connector.keyExchangeConnector;
import com.cuubez.key.exchange.IKeyAgreement;

@SuppressWarnings("unused")
public class DHKeyAgreement implements IKeyAgreement{
	
	private keyExchangeConnector fileWriter = new keyExchangeConnector();
	
	private DHParameterSpec dhKeySpec;
		
	public DHParameterSpec getDhKeySpec() {
		return dhKeySpec;
	}

	public void setDhKeySpec(DHParameterSpec dhKeySpec) {
		this.dhKeySpec = dhKeySpec;
	}

	

	public Object generateKeyInitializer() {
		// TODO Auto-generated method stub
		//DHParameterSpec dhParams = new DHParameterSpec(p1024, g1024);
		try {
		DHParameterInitializer dhParams = new DHParameterInitializer();
		DHParameterSpec dhKeySpec = dhParams.initialize();
		setDhKeySpec(dhKeySpec);
        KeyAgreement keyAgree = KeyAgreement.getInstance("DH", "BC");
        return keyAgree;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object getKeyPair() {
		try {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DH", "BC");
		keyGen.initialize(getDhKeySpec(), Utils.createFixedRandom());
		KeyPair pair = keyGen.generateKeyPair();
		return pair;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	public byte[] writePublicKey(KeyPair keyPair,String fileName) {
		// TODO Auto-generated method stub
		X509EncodedKeySpec pubX509 = null;
		try {
			
		 byte[]  pubEnc = keyPair.getPublic().getEncoded();
		 pubX509 = new X509EncodedKeySpec(pubEnc);
		 
		 //Dump Public Key before writing to file from DH Agreement                 
         //System.out.println(fileName+" Public Key before writing to file from DH Agreement "+Utils.toHex(pubEnc));
         
//		 fileWriter.writeToFile(pubX509.getEncoded(),fileName);
         
        // Store Public Key sample.
 		//X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
 		//FileOutputStream fos = new FileOutputStream(path + "/public.key");
 		//fos.write(x509EncodedKeySpec.getEncoded());
 		//fos.close();
                  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pubX509.getEncoded();
	}
	
	

	public DHPublicKey readPublicKey(String fileName, byte[] rawPublicKey) {
		// TODO Auto-generated method stub
		try {
			
           
//			byte[] key = fileWriter.readFile(fileName);
			
			//KeyFactory keyFactory = KeyFactory.getInstance("DH", "BC");
			//X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(key);
			//PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
			
			//KeyFactory          keyFac = KeyFactory.getInstance("DH", "BC");
	        //X509EncodedKeySpec  pubX509 = new X509EncodedKeySpec(key);
	        //PublicKey         pubKey = (PublicKey) keyFac1.generatePublic(pubX509);
	         
			DHPublicKey publicKey = (DHPublicKey) KeyFactory.getInstance("DH", "BC").generatePublic(new X509EncodedKeySpec(rawPublicKey));
	         
			//Dump public key after reading from file
			//System.out.println(fileName + "public key after reading from DHAgreement "+Utils.toHex(publicKey.getEncoded()));
			
            return publicKey;
	        
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}


}