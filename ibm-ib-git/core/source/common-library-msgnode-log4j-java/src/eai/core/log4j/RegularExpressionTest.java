package eai.core.log4j;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Not used just trying regex out :-)
 */
public class RegularExpressionTest {
	private static Set<String> supportedTokens = new HashSet<String>();
	private final static String exception = "Big issue";
	final static String root = "Blah blah Big issue Blah blah";
	final static String str = "There was an exception ${ExcEption} on line ${LINE} and the root element is \"${rOOt}\"";
	public final static String START_TOKEN = "\\$\\{(";
	public final static String END_TOKEN = ")\\}";

	
	static {
		supportedTokens.add("exception");
    	supportedTokens.add("body");
	}
	
	public static void anotherway() {
    	Pattern pattern = Pattern.compile("\\$\\{([A-Za-z]*)(\\.)?([^}]*)?\\}", Pattern.CASE_INSENSITIVE);
    	String localStr = str;
        Matcher matcher = pattern.matcher(localStr);
        Set<Map.Entry<String, String>> matches = new HashSet<Map.Entry<String, String>>();
        
        //Loop through tokens and get distinct supported tokens 
        while (matcher.find()) {
        	System.out.println("matcher.group(1): " + matcher.group(1));
        	System.out.println("matcher.group(3): " + matcher.group(3));
        	if (supportedTokens.contains(matcher.group(1).toLowerCase())) {
        		Map.Entry<String, String> tokenPair = new AbstractMap.SimpleEntry<String, String>(matcher.group(1), matcher.group(3));
            	matches.add(tokenPair);
        	}
        }
        
        //Replace the tokens with values
        for (Map.Entry<String, String> match : matches) {
        	System.out.println(START_TOKEN + match.getKey() + "." + match.getValue() + END_TOKEN);
        	if (match.getKey().equalsIgnoreCase("exception")) {
        		System.out.println("token matched");
        		if (match.getValue()==null) {
        			localStr = localStr.replaceAll(START_TOKEN + match.getKey() + "\\." + match.getValue() + END_TOKEN, exception);
        		} else {
        			localStr = localStr.replaceAll(START_TOKEN + match.getKey() + END_TOKEN, exception);
        		}
        	}
        }
        System.out.println(localStr);
    }
	
	private static String getTokenValue(String token) {
		if (token.equals("exception")) {
			return exception;
		} else if (token.equals("root")) {
			return root;
		}
		return token;
	}
	
	public static void oneway() {
		String localStr = str;
		Pattern pattern;
		Matcher matcher;
        for (String token : supportedTokens) {
        	pattern = Pattern.compile(START_TOKEN + token + END_TOKEN, Pattern.CASE_INSENSITIVE);
        	matcher = pattern.matcher(localStr);
        	if (matcher.find()) {
        		System.out.println("found" + matcher.group());
            	localStr = matcher.replaceAll(getTokenValue(token));
        	}
        }
        System.out.println(localStr);
	}
}
