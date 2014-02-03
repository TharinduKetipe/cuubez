package com.cuubez.key;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import com.cuubez.key.Utils;
import com.cuubez.key.exchange.IDHOriginator;
import com.cuubez.key.exchange.dh.DHOriginator;
import com.cuubez.key.exchange.ecdh.ECDHOriginator;
import com.cuubez.key.exchange.ecmqv.ECMQVOriginator;
import com.cuubez.key.service.KeyAgreementService;

@SuppressWarnings("unused")
public class TestClient {
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
		
		KeyAgreementService service = new KeyAgreementService();
		
		//DHOriginator dhOriginator = (DHOriginator) service.initiateKeyAgreement(KeyAgreementService.DHALGO, KeyAgreementService.ORIGINATOR);
		//ECDHOriginator ecdhOriginator = (ECDHOriginator) service.initiateKeyAgreement(KeyAgreementService.ECDHALGO, KeyAgreementService.ORIGINATOR);
		ECMQVOriginator ecmvqOriginator = (ECMQVOriginator) service.initiateKeyAgreement(KeyAgreementService.ECMQVALGO, KeyAgreementService.ORIGINATOR);
		
		//dhOriginator.generateKeyAgreement();
		//ecdhOriginator.generateKeyAgreement();
		ecmvqOriginator.generateKeyAgreement();
		
		//This is to simulate,where sever triggers IT's generatesecret key method up on receiving clients key agreement initilization
		TestServer test = new TestServer();
		test.generateSecretKey();
		
		//byte [] ClientSharedKey = dhOriginator.originatorSharedSecret();
		//byte [] ClientSharedKey = ecdhOriginator.originatorSharedSecret();
//		byte [] ClientSharedKey = ecmvqOriginator.originatorSharedSecret();
		
		MessageDigest	hash = MessageDigest.getInstance("SHA1", "BC");
		
		//xxoriginator.originatorSharedSecret() returns required aShared key as byte array
		//xxreciever.receiverSharedSecret() returns required bShared key as byte array
		
//        byte[] originatorShared = hash.digest(ClientSharedKey);
        		
        //Following two needs to be the same
//        System.out.println("Dump ClientShared shared secret " +Utils.toHex(originatorShared));
       
		
	}
}
