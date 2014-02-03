package com.cuubez.key;

import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import com.cuubez.key.Utils;
import com.cuubez.key.exchange.dh.DHOriginator;
import com.cuubez.key.exchange.dh.DHReceiver;

@SuppressWarnings("unused")
public class DHTest {

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
		// TODO Auto-generated method stub
				
		DHOriginator originator = new DHOriginator();
		DHReceiver reciever = new DHReceiver();
		
        byte [] orginatorPubKey =	originator.generateKeyAgreement();
		byte [] recieverPubKey =   reciever.generateKeyAgreement();
		
		byte[] coded = Base64.encode(orginatorPubKey);
        String strCoded = new String(coded);           
		
        byte[] decoded = Base64.decode(strCoded);
        
		MessageDigest	hash = MessageDigest.getInstance("SHA1", "BC");
		
		//originator.originatorSharedSecret() returns required aShared key as byte array
		//reciever.receiverSharedSecret() returns required bShared key as byte array
		
       byte[] originatorShared = hash.digest(originator.originatorSharedSecret(recieverPubKey));
       byte[] recieverShared = hash.digest(reciever.receiverSharedSecret(decoded));
		
        //Following two needs to be the same
       System.out.println("Dump originator shared secret " +Utils.toHex(originatorShared));
       System.out.println("Dump reciever shared secret " +Utils.toHex(recieverShared));
        
		}

}
