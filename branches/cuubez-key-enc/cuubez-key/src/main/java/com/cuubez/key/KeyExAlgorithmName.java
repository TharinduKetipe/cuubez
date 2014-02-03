/**
 * 
 */
package com.cuubez.key;

/**
 * @author ruwan
 *
 */
public enum KeyExAlgorithmName {

	DHALGO(0),ECDHALGO(1),ECMQVALGO(2);
	
    private int numVal;

    KeyExAlgorithmName(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
