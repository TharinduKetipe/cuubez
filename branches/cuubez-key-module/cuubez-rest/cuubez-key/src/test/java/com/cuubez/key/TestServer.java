package com.cuubez.key;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import com.cuubez.key.Utils;
import com.cuubez.key.exchange.dh.DHOriginator;
import com.cuubez.key.exchange.dh.DHReceiver;
import com.cuubez.key.exchange.ecdh.ECDHOriginator;
import com.cuubez.key.exchange.ecdh.ECDHReceiver;
import com.cuubez.key.exchange.ecmqv.ECMQVOriginator;
import com.cuubez.key.exchange.ecmqv.ECMQVReceiver;
import com.cuubez.key.service.KeyAgreementService;

@SuppressWarnings("unused")
public class TestServer {
	
	DHReceiver dhReciever;
	ECDHReceiver ecdhReciever;
	ECMQVReceiver ecmvqReciever;
	
	
	public TestServer() {
		super();
		// This constuctor used to simulate Server key Agreement initilazation up on client invokation
		KeyAgreementService service = new KeyAgreementService();
		
		//dhReciever = (DHReceiver) service.initiateKeyAgreement(KeyAgreementService.DHALGO, KeyAgreementService.RECIEVER);
		//ecdhReciever = (ECDHReceiver) service.initiateKeyAgreement(KeyAgreementService.ECDHALGO, KeyAgreementService.RECIEVER);
		ecmvqReciever = (ECMQVReceiver) service.initiateKeyAgreement(KeyAgreementService.ECMQVALGO, KeyAgreementService.RECIEVER);
		
		//dhReciever.generateKeyAgreement();
		//ecdhReciever.generateKeyAgreement();
		ecmvqReciever.generateKeyAgreement();
	}

	
	public void generateSecretKey() throws NoSuchAlgorithmException, NoSuchProviderException {
		//byte [] ServerSharedKey = dhReciever.receiverSharedSecret();
		//byte [] ServerSharedKey = ecdhReciever.receiverSharedSecret();
//		byte [] ServerSharedKey = ecmvqReciever.receiverSharedSecret();
		
		MessageDigest	hash = MessageDigest.getInstance("SHA1", "BC");
		
		//xxoriginator.originatorSharedSecret() returns required aShared key as byte array
		//xxreciever.receiverSharedSecret() returns required bShared key as byte array
		
//        byte[] receiverShared = hash.digest(ServerSharedKey);
        		
        //Following two needs to be the same
//        System.out.println("Dump SerevrShared shared secret " +Utils.toHex(receiverShared));
		
	}
}
