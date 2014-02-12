/**
 * 
 */
package com.cuubez.client.security;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

/**
 * 
 */
public class ClientKeyRepositoryService {

	private final Map<String, SecretKey> keyMap;
	public static ClientKeyRepositoryService instance = null;

	public static ClientKeyRepositoryService getInstance() {
		if (instance == null) {
			instance = new ClientKeyRepositoryService();
		}
		return instance;
	}

	private ClientKeyRepositoryService() {
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

	public void emptyKeyRepository() {
		keyMap.clear();
	}

	@Override
	public String toString() {
		return "ClientKeyRepositoryService [keyMap=" + keyMap + "]";
	}
}
