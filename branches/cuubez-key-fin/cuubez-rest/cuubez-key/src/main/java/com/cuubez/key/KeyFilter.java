/*
 * 
 */
package com.cuubez.key;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Map;

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

        if(keyExAlgoName != null && publicKey != null){
        	
        	if(keyService.retrieveSecretSharedKeyForPrincipal(principal) == null){
        		ECMQVReceiver receiver = (ECMQVReceiver)keyAgreementService.initiateKeyAgreement(Integer.parseInt(keyExAlgoName), KeyAgreementService.RECIEVER);
		
		        Map<String, byte[]> serverPublicKeys = receiver.generateKeyAgreement();
		        
		        byte[] decoded = Base64.decode(publicKey);
		        byte[] decoded2 = Base64.decode(publicKey2);
		        byte[] sharedSecretKey = receiver.receiverSharedSecret(decoded,decoded2);
		        
		        //Attempt to view server side shared secret via tocat logging
		        MessageDigest hash;
		        
				try {
					hash = MessageDigest.getInstance("SHA1", "BC");
					byte[] receiverShared = hash.digest(sharedSecretKey);
					log.info("Agreed shared secret (Hash value of actual key on Server side) with ECMQV :: " +Utils.toHex(receiverShared));
		            				
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchProviderException e) {
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
