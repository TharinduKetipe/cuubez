package com.cuubez.key.connector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

//This is the extension to be used to send public keys and receive public keys
//This was replaced by Client and server side implementation on sending and receiving Keys

public class keyExchangeConnector {
	
	public void writeToFile(byte[] stream, String fileName) {
		try {
			
			FileOutputStream fos = new FileOutputStream("D:\\"+fileName);
			fos.write(stream);
			fos.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public byte[] readFile(String fileName) {
		try {
			
			// Read Public Key.
			File filePublicKey = new File("D:\\"+fileName);
			FileInputStream fis = new FileInputStream("D:\\"+fileName);
			byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
			fis.read(encodedPublicKey);
			fis.close();
			return encodedPublicKey;
			
		} catch(Exception w) {
			w.printStackTrace();
		}
		return null;
	}

}