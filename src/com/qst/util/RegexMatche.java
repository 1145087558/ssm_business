package com.qst.util;

import java.util.regex.Pattern;

public class RegexMatche {

	public static boolean isEmail(String email){
		String pattern = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
		return Pattern.matches(pattern, email);
		
	}
	
	public static boolean isPhone(String phone){
		if(phone == null){
            return false;
        }
        if(phone.length() != 11){
            return false;
        }
		String pattern ="^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
		return Pattern.matches(pattern, phone);
	}

}
