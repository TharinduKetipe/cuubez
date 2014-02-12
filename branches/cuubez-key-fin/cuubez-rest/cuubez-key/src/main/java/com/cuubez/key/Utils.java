package com.cuubez.key;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Utils

{

	private static String digits = "0123456789abcdef";

	public static String toHex(byte[] data, int length) {
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i != length; i++) {
			int v = data[i] & 0xff;

			buf.append(digits.charAt(v >> 4));
			buf.append(digits.charAt(v & 0xf));
		}

		return buf.toString();
	}

	public static String toHex(byte[] data) {
		return toHex(data, data.length);
	}

	public static SecretKey createKeyForAES(int bitLength, SecureRandom random)
			throws NoSuchAlgorithmException, NoSuchProviderException {
		KeyGenerator generator = KeyGenerator.getInstance("AES", "BC");

		generator.init(256, random);

		return generator.generateKey();
	}

	public static IvParameterSpec createCtrIvForAES(int messageNumber,
			SecureRandom random) {
		byte[] ivBytes = new byte[16];

		// initially randomize

		random.nextBytes(ivBytes);

		// set the message number bytes

		ivBytes[0] = (byte) (messageNumber >> 24);
		ivBytes[1] = (byte) (messageNumber >> 16);
		ivBytes[2] = (byte) (messageNumber >> 8);
		ivBytes[3] = (byte) (messageNumber >> 0);

		// set the counter bytes to 1

		for (int i = 0; i != 7; i++) {
			ivBytes[8 + i] = 0;
		}

		ivBytes[15] = 1;

		return new IvParameterSpec(ivBytes);
	}

	public static String toString(byte[] bytes, int length) {
		char[] chars = new char[length];

		for (int i = 0; i != chars.length; i++) {
			chars[i] = (char) (bytes[i] & 0xff);
		}

		return new String(chars);
	}

	public static String toString(byte[] bytes) {
		return toString(bytes, bytes.length);
	}

	public static byte[] toByteArray(String string) {
		byte[] bytes = new byte[string.length()];
		char[] chars = string.toCharArray();

		for (int i = 0; i != chars.length; i++) {
			bytes[i] = (byte) chars[i];
		}

		return bytes;
	}

	@SuppressWarnings({ "serial", "unused" })
	private static class FixedRand extends SecureRandom {
		MessageDigest sha;
		byte[] state;

		FixedRand() {
			try {
				this.sha = MessageDigest.getInstance("SHA-1");
				this.state = sha.digest();
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException("can't find SHA-1!");
			}
		}

		public void nextBytes(byte[] bytes) {
			int off = 0;

			sha.update(state);

			while (off < bytes.length) {
				state = sha.digest();

				if (bytes.length - off > state.length) {
					System.arraycopy(state, 0, bytes, off, state.length);
				} else {
					System.arraycopy(state, 0, bytes, off, bytes.length - off);
				}

				off += state.length;

				sha.update(state);
			}
		}
	}

	public static SecureRandom createFixedRandom() {
		return new SecureRandom();
		// return new FixedRand();
	}
}
