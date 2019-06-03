package br.com.leandro.neomind.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

	public static boolean isValid(String email) {
		
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(email);
		return m.matches();		
		
	}
	
}
