package com.fgl.eai;

public class SpecialCharRemove {
	/**
	 * Method to add a check for special characters
	 */
	public static String specialCharRemove(String stringValue){	
		
		return stringValue.replaceAll("\\W","");
		
	}

}
