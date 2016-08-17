package com.org.accenture.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;


public class MaskingUtils {
	
	static public String maskString(String inString, List<MaskingFactoryConfig> factoryConfig) {
	       // prime the internal variables to be used during processing
	       String stringToMask = inString;
	       String maskedString = inString;
		   
	       // iterate through each pattern and mask any matches leaving the last visible characters
	       for(MaskingFactoryConfig maskingFactoryConfig : factoryConfig) {
	    	   Integer inVisibleCharacters = maskingFactoryConfig.getPostInvisibleCount();
	    	   Integer inpreVisibleCharacters = maskingFactoryConfig.getPreInvisibleCount();
	    	   // validate the passed in variables
		       if (inString == null || inString.length() < 1 ||
		    		   maskingFactoryConfig.getRegex() == null || 
		    				   StringUtils.isEmpty(maskingFactoryConfig.getRegex()) || maskingFactoryConfig.getMaskingChar() == null) continue;
			       if (maskingFactoryConfig.getPreInvisibleCount() < 0) inVisibleCharacters = 0;
			       if (maskingFactoryConfig.getPostInvisibleCount() < 0) inpreVisibleCharacters = 0;
	    	   
	           Pattern p = Pattern.compile(maskingFactoryConfig.getRegex());
	           Matcher m = p.matcher(stringToMask);
	           while(m.find()) {
	               // find the start and end indexes of the match
	        	   Integer startIdx = m.start();
	        	   Integer endIdx = m.end();
	               
	               // extract the matched string
	               String patternMatch = stringToMask.substring(startIdx, endIdx);                    
	               
	               // mask the string leaving any visible characters
	               String partToBeMasked = patternMatch.substring(0 + inpreVisibleCharacters, patternMatch.length() - inVisibleCharacters);                                                             
	               //SONAR VIOLATION- Performance - Method concatenates strings using + in a loop
	               //String mask = "";
	               StringBuffer maskBuffer = new StringBuffer();
	               for(Integer i = 0; i < partToBeMasked.length(); i++) {
	                   //mask += maskingFactoryConfig.getMaskingChar();
	            	   maskBuffer.append(maskingFactoryConfig.getMaskingChar());
	               }
	               String mask = maskBuffer.toString();
	               // concatenate mask string with the last visible characters               
	               String maskedNumber = patternMatch.substring(0, inpreVisibleCharacters)+ mask + patternMatch.substring(inpreVisibleCharacters+mask.length());                   
	               // replace the the card number with masked number
	               maskedString = maskedString.replace(patternMatch, maskedNumber);
	           }               
	       }       
	       return maskedString;     
	   }
	
	 private static String testMaskStirng()
	 	{
		// create a pattern for matching, in this case we are looking for visa numbers
		List<MaskingFactoryConfig> listPatterns = new ArrayList<MaskingFactoryConfig>();
		// test to make sure the credit card number is masked leaving the last 4 digits
		MaskingFactoryConfig mask =  new MaskingFactoryConfig();
		mask.setPreInvisibleCount(8);
		mask.setPostInvisibleCount(10);
		mask.setMaskingChar("*");
		mask.setRegex("<Address:.[^>]*>.{0}[^<]*</Address:.[^>]*>");
		listPatterns.add(mask);
		 return MaskingUtils.maskString("<Person:dateOfBirth>1935-09-11</Person:dateOfBirth><Person:title>Mr</Person:title><Address:xy>4111111111111111</Address:xy>", listPatterns); 
	 	}
	 
	 public static void main(String args[]){
		 System.out.print(testMaskStirng());
	 }

}
