package com.cuubez.key.exchange.dh;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPublicKey;

import com.cuubez.key.Utils;
import com.cuubez.key.exchange.IDHOriginator;

@SuppressWarnings("unused")
public class DHOriginator implements IDHOriginator{ 
	
	
	
	
	DHKeyAgreement oAgreement;
	KeyAgreement oAgree;
	KeyPair oPair;
	 
	public byte [] originatorSharedSecret(byte[] rawPublicKey) {
		try {
			
		//dump Receiver public key
		//System.out.println("Receiver public key after reading from DHOriginator "+Utils.toHex(oAgreement.readPublicKey("receiver").getEncoded()));
				
		oAgree.doPhase(oAgreement.readPublicKey("receiver",rawPublicKey), true);
		
        // generate the key bytes
		byte [] aSharedSecret = oAgree.generateSecret();
		
        //MessageDigest	hash = MessageDigest.getInstance("SHA1", "BC");
        //byte[] aShared = hash.digest(oAgree.generateSecret());
        
        //Dump originator shared secret
        //System.out.println("Dump originator shared secret " +Utils.toHex(aShared));
		
        // use this aShared as shared secret Key on originator side
        return aSharedSecret;
        
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
			
		
	}
	
	public byte[] generateKeyAgreement() {
		try {
		oAgreement = new DHKeyAgreement();
		oAgree = (KeyAgreement) oAgreement.generateKeyInitializer();
		oPair = (KeyPair) oAgreement.getKeyPair();
		
		// two party agreement
		oAgree.init(oPair.getPrivate());
		
		//dump private key
		//System.out.println("dump Originator private key on making "+Utils.toHex(oPair.getPrivate().getEncoded()));
		
		return oAgreement.writePublicKey(oPair, "originator");
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}	

}
