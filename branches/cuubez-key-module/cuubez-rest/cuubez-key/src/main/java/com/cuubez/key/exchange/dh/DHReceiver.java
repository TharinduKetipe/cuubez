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
import com.cuubez.key.exchange.IDHReceiver;

@SuppressWarnings("unused")
public class DHReceiver implements IDHReceiver{
	
	

		// TODO Auto-generated method stub
		
	    DHKeyAgreement rAgreement;
		KeyAgreement rAgree;
		KeyPair rPair;
		
		public byte [] receiverSharedSecret(byte[] rawPublicKey) {
			try {
			
			
			//dump originator public key
			//System.out.println("originator public key after reading from DHReceiver "+Utils.toHex(rAgreement.readPublicKey("originator").getEncoded()));
			
			rAgree.doPhase(rAgreement.readPublicKey("originator", rawPublicKey), true);

	        // generate the key bytes
			byte[] bSharedSecret = rAgree.generateSecret();
			
	        //MessageDigest	hash = MessageDigest.getInstance("SHA1", "BC");
	        //byte[] bShared = hash.digest(rAgree.generateSecret());
	        
	        //Dump receiver shared secret
	        //System.out.println("Dump receiver shared secret "+Utils.toHex(bShared));
	        // use this bShared as shared secret Key on Receiver side
	        
			return bSharedSecret;
			
			} 
			
			catch(Exception e) {
				e.printStackTrace();
			}
			return null;
			
		}
		
		public byte[] generateKeyAgreement() {
			try {
			rAgreement = new DHKeyAgreement();
			rAgree = (KeyAgreement) rAgreement.generateKeyInitializer();
			rPair = (KeyPair) rAgreement.getKeyPair();
			
			// two party agreement
			rAgree.init(rPair.getPrivate());
			
			//dump private key
			//System.out.println("dump Receiver private key on making "+Utils.toHex(rPair.getPrivate().getEncoded()));
			
			return rAgreement.writePublicKey(rPair, "receiver");
			
			} catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
	}