package com.chengtao.culture.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	private static final String KEY_MD5 = "MD5";
	public static String getMD5Password(String inputStr) {
		try {
			MessageDigest mdAlgorithm = MessageDigest.getInstance(KEY_MD5);
			mdAlgorithm.update(inputStr.getBytes());
			byte[] digest = mdAlgorithm.digest();
			StringBuilder hexString = new StringBuilder();
			for (byte aDigest : digest) {
				inputStr = Integer.toHexString(0xFF & aDigest);

				if (inputStr.length() < 2) {
					inputStr = "0" + inputStr;
				}
				hexString.append(inputStr);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e1) {
			return null;
		}
	}
}
