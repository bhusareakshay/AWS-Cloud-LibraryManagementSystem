package com.neu.library.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class ValidateUtil {

	public ValidateUtil() {
		
		
	}
	
	public boolean varifyEmail(String email) {
		
boolean isValid = false;
		
		//Email pattern (abc@abc.com , abc@abc.co.in)
		Pattern emailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+$");
		
		//Match input email string with email pattern and return boolean
		Matcher matcher = emailPattern.matcher(email);
		isValid = matcher.find();
return isValid;
		
	}
	
	public boolean verifyPassword(String password) {
		
		boolean isValid = false;
		 Pattern passwordpattern = Pattern.compile("\\s");
		 Matcher match = passwordpattern.matcher(password);
		if(password.length() > 1 && !match.find()) {
			isValid = true;
		}
		else {
			isValid = false;
		}
		
		return isValid;
	}
	
	

}
