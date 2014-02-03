package com.cuubez.key;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

import com.cuubez.key.Utils;
import com.cuubez.key.exchange.ecmqv.ECMQVOriginator;
import com.cuubez.key.exchange.ecmqv.ECMQVReceiver;

public class ECMQVTest {
			
		public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
			// TODO Auto-generated method stub
			ECMQVOriginator originator = new ECMQVOriginator();
			ECMQVReceiver reciever = new ECMQVReceiver();
			
			Map<String, byte[]> orikeys = originator.generateKeyAgreement();
			Map<String, byte[]> recikeys = reciever.generateKeyAgreement();
			
			MessageDigest	hash = MessageDigest.getInstance("SHA1", "BC");
			
			//originator.originatorSharedSecret() returns required aShared key as byte array
			//reciever.receiverSharedSecret() returns required bShared key as byte array
			
	        byte[] originatorShared = hash.digest(originator.originatorSharedSecret(recikeys.get("key1"), recikeys.get("key2")));
	        byte[] recieverShared = hash.digest(reciever.receiverSharedSecret(orikeys.get("key1"), orikeys.get("key2")));
			
	        //Following two needs to be the same
	        System.out.println("Dump originator shared secret " +Utils.toHex(originatorShared));
	        System.out.println("Dump reciever shared secret " +Utils.toHex(recieverShared));
	        
			}

	}



