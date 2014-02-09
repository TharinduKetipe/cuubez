/*
 * 
 */
package com.cuubez.key;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import com.cuubez.key.exchange.IDHReceiver;
import com.cuubez.key.exchange.ecmqv.ECMQVReceiver;
import com.cuubez.key.service.IKeyAgreementService;
import com.cuubez.key.service.KeyAgreementService;

/**
 * 
 *
 */
public class KeyFilter implements Filter {

    
	private static Log log = LogFactory.getLog(KeyFilter.class);

    private final static String PUBLIC_KEY = "PublicKey";
    private final static String PUBLIC_KEY2 = "PublicKey2";
	private final static String KEY_EX_ALGO_NAME = "KeyExAlgoName";
	private static final String PRINCIPAL_CUSTOM_HEADER = "principal";
	
	private IKeyAgreementService keyAgreementService = null;
	private KeyService keyService = null;

    /**
	 * 
	 */
    public KeyFilter() {
    	keyAgreementService = new KeyAgreementService();
    	keyService = new KeyService();
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig filterConfig) throws ServletException {
    	
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
    	Security.addProvider(new BouncyCastleProvider());
    	
        String keyExAlgoName = ((HttpServletRequest) request).getHeader(KEY_EX_ALGO_NAME);
        String publicKey = ((HttpServletRequest) request).getHeader(PUBLIC_KEY);
        String publicKey2 = ((HttpServletRequest) request).getHeader(PUBLIC_KEY2);
        String principal = ((HttpServletRequest) request).getHeader(PRINCIPAL_CUSTOM_HEADER);

        log.info("keyExAlgoName numeric using on this :: " +keyExAlgoName);
        
        
        if(keyExAlgoName != null && publicKey != null){
        	
        	log.info("Going to ALGO selection"); 
        	
        if(Integer.parseInt(keyExAlgoName)==2 ) 
        
        {
        	
        	log.info("Algo ECMQV selected"); 
        	
        	//Implement timing mechanism here
        	try {
				Thread.currentThread();
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	//Look at Key Management service to check whether we have a valid key for this client,But currently this will be always true and generate new key each time
        	
        	if(keyService.retrieveSecretSharedKeyForPrincipal(principal) == null)
        	
        	{
        		ECMQVReceiver receiver = (ECMQVReceiver)keyAgreementService.initiateKeyAgreement(Integer.parseInt(keyExAlgoName), KeyAgreementService.RECIEVER);
		
		        Map<String, byte[]> serverPublicKeys = receiver.generateKeyAgreement();
		        
		        byte[] decoded = Base64.decode(publicKey);
		        byte[] decoded2 = Base64.decode(publicKey2);
		        byte[] sharedSecretKey = receiver.receiverSharedSecret(decoded,decoded2);
		        
		        //Check whether Encryption and Decryption with Shared Secret possible
		        //SecretKey originalKey = new SecretKeySpec(sharedSecretKey, 0, sharedSecretKey.length, "AES");
		        		        
		        byte[]          input = new byte[] { 
		                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 
		                0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
		                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 };
		        //byte[]		    keyBytes = new byte[] { 
		        //        0x01, 0x23, 0x45, 0x67, (byte)0x89, (byte)0xab, (byte)0xcd, (byte)0xef };
		        byte[]		    ivBytes = new byte[] { 
		                0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00 };
		        
		        //SecretKeySpec   key = new SecretKeySpec(keyBytes, "DES");
		        SecretKey key = new SecretKeySpec(sharedSecretKey, 0, 8, "DES");
		        IvParameterSpec ivSpec = new IvParameterSpec(new byte[8]);
		        Cipher cipher;
				try {
					cipher = Cipher.getInstance("DES/CBC/PKCS7Padding", "BC");
				

				log.info("input : " + Utils.toHex(input));
		        
		        // encryption pass
		        
		        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
		        
		        byte[] cipherText = new byte[cipher.getOutputSize(ivBytes.length + input.length)];
		        
		        int ctLength = cipher.update(ivBytes, 0, ivBytes.length, cipherText, 0);
		        
		        ctLength += cipher.update(input, 0, input.length, cipherText, ctLength);
		        
		        ctLength += cipher.doFinal(cipherText, ctLength);
		        
		        log.info("cipher: " + Utils.toHex(cipherText, ctLength) + " bytes: " + ctLength);
		        
		        // decryption pass
		        
		        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
		        
		        byte[] buf = new byte[cipher.getOutputSize(ctLength)];
		        
		        int bufLength = cipher.update(cipherText, 0, ctLength, buf, 0);
		        
		        bufLength += cipher.doFinal(buf, bufLength);
		        
		        // remove the iv from the start of the message
		        
		        byte[] plainText = new byte[bufLength - ivBytes.length];
		        
		        System.arraycopy(buf, ivBytes.length, plainText, 0, plainText.length);
		        
		        log.info("plain : " + Utils.toHex(plainText, plainText.length) + " bytes: " + plainText.length);
		        
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchProviderException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidAlgorithmParameterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ShortBufferException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalBlockSizeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BadPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        //End of experiment Encryption and Decryption
				
		        //Attempt to view server side shared secret via tomcat logging
		        MessageDigest hash;
		        
				try {
					hash = MessageDigest.getInstance("SHA1", "BC");
					byte[] receiverShared = hash.digest(sharedSecretKey);
					log.info("Agreed shared secret (Hash value of actual key on Server side) with ECMQV :: " +Utils.toHex(receiverShared));
		            				
				} 
				
				catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				catch (NoSuchProviderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	    		           
		        //TODO : Need implement logic here not to add same principal twice.Need expiration mechanism.
		        //Need a Key management done under key service.
		        //keyService.addSecretKeyWithPrincipal(principal, sharedSecretKey);
		       
		        
                byte[] coded = Base64.encode(serverPublicKeys.get("key1"));
                byte[] coded2 = Base64.encode(serverPublicKeys.get("key2"));
                String strCoded = new String(coded);
                String strCoded2 = new String(coded2);
		        ((HttpServletResponse) response).addHeader(PUBLIC_KEY, strCoded);
		        ((HttpServletResponse) response).addHeader(PUBLIC_KEY2, strCoded2);
	        }
        	
        	else
        	{
        		log.info("Client has unexpired Keys created");
        	}
        }
        	
        	
        if(Integer.parseInt(keyExAlgoName)!=2)
        	 
        	 {
        		 
        		 log.info("Algo ECDH selected"); 
             	
             	if(keyService.retrieveSecretSharedKeyForPrincipal(principal) == null)
             	
             	{
     		        IDHReceiver receiver = (IDHReceiver)keyAgreementService.initiateKeyAgreement(Integer.parseInt(keyExAlgoName), KeyAgreementService.RECIEVER);
     		
     		        byte[] serverPublicKey = receiver.generateKeyAgreement();
     		        
     		        byte[] decoded = Base64.decode(publicKey);
     		        byte[] sharedSecretKey = receiver.receiverSharedSecret(decoded);
     		        
     		        //TODO : You need to define a algorithm to expire shared secret And a method to accomplish retrieve shared secret if not expired
     		        //Trying to put same principal when it's existing creates issues
     		        //keyService.addSecretKeyWithPrinciple(principal, sharedSecretKey);
     		        
     		       MessageDigest hash;
     		       
     		       try {
   					hash = MessageDigest.getInstance("SHA1", "BC");
   					byte[] receiverShared = hash.digest(sharedSecretKey);
   					log.info("Agreed shared secret (Hash value of actual key on Server side) with ECDH :: " +Utils.toHex(receiverShared));
   		            				
     		       } catch (NoSuchAlgorithmException e) {
   					// TODO Auto-generated catch block
   					e.printStackTrace();
     		       } catch (NoSuchProviderException e) {
   					// TODO Auto-generated catch block
   					e.printStackTrace();
     		       }
     		       
                     byte[] coded = Base64.encode(serverPublicKey);
                     String strCoded = new String(coded);  
     		        ((HttpServletResponse) response).addHeader(PUBLIC_KEY, strCoded);
     	        }
             	
             	else
             	{
             		log.info("Client has unexpired Keys created");
             	}

             }
        	 
        }
        
        
        chain.doFilter(request, response);
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {

    }

}
