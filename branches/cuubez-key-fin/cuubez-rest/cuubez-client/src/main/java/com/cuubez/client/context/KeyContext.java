/**
 * 
 */
package com.cuubez.client.context;

import java.util.Arrays;

import com.cuubez.key.exchange.IDHOriginator;
import com.cuubez.key.exchange.ecmqv.ECMQVOriginator;

/**
 * @author ruwan
 * 
 */
public class KeyContext {

	private byte[] clientPublicKey;
	private byte[] clientPublicKey2;
	private byte[] serverPublicKey;
	private byte[] serverPublicKey2;

	private byte[] sharedSecretKey;
	private ECMQVOriginator ecmvqOriginator;
	private IDHOriginator idhOriginator;

	/**
	 * @return the clientPublicKey
	 */
	public byte[] getClientPublicKey() {
		return clientPublicKey;
	}

	/**
	 * @param clientPublicKey
	 *            the clientPublicKey to set
	 */
	public void setClientPublicKey(byte[] clientPublicKey) {
		this.clientPublicKey = clientPublicKey;
	}

	/**
	 * @return the serverPublicKey
	 */
	public byte[] getServerPublicKey() {
		return serverPublicKey;
	}

	/**
	 * @param serverPublicKey
	 *            the serverPublicKey to set
	 */
	public void setServerPublicKey(byte[] serverPublicKey) {
		this.serverPublicKey = serverPublicKey;
	}

	/**
	 * @return the sharedSecretKey
	 */
	public byte[] getSharedSecretKey() {
		return sharedSecretKey;
	}

	/**
	 * @param sharedSecretKey
	 *            the sharedSecretKey to set
	 */
	public void setSharedSecretKey(byte[] sharedSecretKey) {
		this.sharedSecretKey = sharedSecretKey;
	}

	/**
	 * @return the clientPublicKey2
	 */
	public byte[] getClientPublicKey2() {
		return clientPublicKey2;
	}

	/**
	 * @param clientPublicKey2
	 *            the clientPublicKey2 to set
	 */
	public void setClientPublicKey2(byte[] clientPublicKey2) {
		this.clientPublicKey2 = clientPublicKey2;
	}

	/**
	 * @return the ecmvqOriginator
	 */
	public ECMQVOriginator getEcmvqOriginator() {
		return ecmvqOriginator;
	}

	/**
	 * @param ecmvqOriginator
	 *            the ecmvqOriginator to set
	 */
	public void setEcmvqOriginator(ECMQVOriginator ecmvqOriginator) {
		this.ecmvqOriginator = ecmvqOriginator;
	}

	/**
	 * @return the serverPublicKey2
	 */
	public byte[] getServerPublicKey2() {
		return serverPublicKey2;
	}

	/**
	 * @param serverPublicKey2
	 *            the serverPublicKey2 to set
	 */
	public void setServerPublicKey2(byte[] serverPublicKey2) {
		this.serverPublicKey2 = serverPublicKey2;
	}

	public IDHOriginator getIdhOriginator() {
		return idhOriginator;
	}

	/**
	 * @param idhOriginator
	 *            the idhOriginator to set
	 */
	public void setIdhOriginator(IDHOriginator idhOriginator) {
		this.idhOriginator = idhOriginator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KeyContext [clientPublicKey="
				+ Arrays.toString(clientPublicKey) + ", clientPublicKey2="
				+ Arrays.toString(clientPublicKey2) + ", serverPublicKey="
				+ Arrays.toString(serverPublicKey) + ", serverPublicKey2="
				+ Arrays.toString(serverPublicKey2) + ", sharedSecretKey="
				+ Arrays.toString(sharedSecretKey) + ", ecmvqOriginator="
				+ ecmvqOriginator + "]";
	}

}
