package com.cuubez.key;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import com.cuubez.key.Utils;
import com.cuubez.key.exchange.ecdh.ECDHOriginator;
import com.cuubez.key.exchange.ecdh.ECDHReceiver;

public class ECDHTest {
	
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
		// TODO Auto-generated method stub
		ECDHOriginator originator = new ECDHOriginator();
		ECDHReceiver reciever = new ECDHReceiver();
				
        byte [] orginatorPubKey =	originator.generateKeyAgreement();
		byte [] recieverPubKey =   reciever.generateKeyAgreement();
		
		MessageDigest	hash = MessageDigest.getInstance("SHA1", "BC");
		
		//originator.originatorSharedSecret() returns required aShared key as byte array
		//reciever.receiverSharedSecret() returns required bShared key as byte array
		
        byte[] originatorShared = hash.digest(originator.originatorSharedSecret(recieverPubKey));
        byte[] recieverShared = hash.digest(reciever.receiverSharedSecret(orginatorPubKey));
//		
//        //Following two needs to be the same
        System.out.println("Dump originator shared secret " +Utils.toHex(originatorShared));
        System.out.println("Dump reciever shared secret " +Utils.toHex(recieverShared));
		}

}