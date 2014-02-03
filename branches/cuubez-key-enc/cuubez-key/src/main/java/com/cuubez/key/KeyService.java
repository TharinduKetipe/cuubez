/**
 * 
 */
package com.cuubez.key;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class KeyService {

	private final Map<String, byte[]> keyMap;


	/**
     * 
     */
	public KeyService() {
		keyMap = new HashMap<String, byte[]>();
	}

	public byte[] retrieveSecretSharedKeyForPrincipal(String principal) {

		return keyMap.get(principal);
	}

	public void addSecretKeyWithPrincipal(String principal, byte[] secretKey) {
		if (!keyMap.containsKey(principal)) {
			keyMap.put(principal, secretKey);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KeyService [keyMap=" + keyMap + "]";
	}
}
