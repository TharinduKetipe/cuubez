package com.cuubez.key.exchange.ecmqv;

import java.security.KeyPair;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.crypto.KeyAgreement;

import org.bouncycastle.jce.spec.MQVPrivateKeySpec;
import org.bouncycastle.jce.spec.MQVPublicKeySpec;

import com.cuubez.key.Utils;


@SuppressWarnings("unused")
public class ECMQVReceiver {
	ECMQVKeyAgreement rAgreement;
	KeyAgreement rAgree;
	KeyPair r1Pair;
	KeyPair r2Pair;
	
	public byte [] receiverSharedSecret(byte[] rawPublicKey1,byte[] rawPublicKey2) {
		try {
		
		
		//dump originator public key
		//System.out.println("originator public key1 after reading from DHReceiver "+Utils.toHex(rAgreement.readPublicKey("originatorPub1").getEncoded()));
		//System.out.println("originator public key2 after reading from DHReceiver "+Utils.toHex(rAgreement.readPublicKey("originatorPub1").getEncoded()));
		
		MQVPublicKeySpec mvqPublicKeySpec = new MQVPublicKeySpec (rAgreement.readPublicKey("originatorPub1",rawPublicKey1),rAgreement.readPublicKey("originatorPub2",rawPublicKey2));
		
		rAgree.doPhase(mvqPublicKeySpec, true);

        // generate the key bytes
		byte[] bSharedSecre = rAgree.generateSecret();
        //MessageDigest	hash = MessageDigest.getInstance("SHA1", "BC");
        //byte[] bShared = hash.digest(rAgree.generateSecret());
        
        //Dump receiver shared secret
        //System.out.println("Dump receiver shared secret "+Utils.toHex(bShared));
        // use this bShared as shared secret Key on Receiver side
        
		return bSharedSecre;
		
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public Map<String, byte[]> generateKeyAgreement() {
		Map<String, byte[]> keys = new HashMap<String, byte[]>();
		
		try {
		rAgreement = new ECMQVKeyAgreement();
		rAgree = (KeyAgreement) rAgreement.generateKeyInitializer();
		r1Pair = (KeyPair) rAgreement.getKeyPair();
		r2Pair = (KeyPair) rAgreement.getKeyPair();
		
		// two party agreement
		
		rAgree.init(new MQVPrivateKeySpec(r1Pair.getPrivate(), r2Pair.getPrivate(), r2Pair.getPublic()));
		
		//dump private key
		//System.out.println("dump Receiver private key on making "+Utils.toHex(rPair.getPrivate().getEncoded()));
		
		keys.put("key1",rAgreement.writePublicKey(r1Pair, "receiverPub1"));
		keys.put("key2",rAgreement.writePublicKey(r2Pair, "receiverPub2"));
		
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return keys;
	}

}
