package com.chengtao.utils;

public class StringUtils {
	public static boolean isStrNotNull(String...strings) {
		for (String string : strings) {
			if (string == null || string.equals("")) {
				return false;
			}
		}
		return true;
	}
}
