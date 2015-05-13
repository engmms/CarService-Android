package com.ruhoon.jiayu.merchant.staticdata;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password {

	public static boolean isRightPassword(String str) {

		Pattern p = Pattern.compile("^[A-Za-z0-9]{6,16}+$");
		Matcher m = p.matcher(str);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}

	}
}
