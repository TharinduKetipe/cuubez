/**
 * 
 */
package com.cuubez.key;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class KeyRepositoryService {

	private final Map<String, SecretKey> keyMap;
    public static KeyRepositoryService instance = null;

    public static KeyRepositoryService getInstance() {
        if (instance == null) {
            instance = new KeyRepositoryService();
        }
        return instance;
    }

    private KeyRepositoryService() {
		keyMap = new HashMap<String, SecretKey>();
	}

	public SecretKey retrieveSecretSharedKeyForPrincipal(String principal) {

		return keyMap.get(principal);
	}

	public void addSecretKeyWithPrincipal(String principal, SecretKey secretKey) {
		if (!keyMap.containsKey(principal)) {
			keyMap.put(principal, secretKey);
		}
	}


	@Override
	public String toString() {
		return "KeyRepositoryService [keyMap=" + keyMap + "]";
	}
}
