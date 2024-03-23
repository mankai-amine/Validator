
// 2482903 Mohamed Amine Mankai   &  2457647 Iana Setrakova

public class Validator {

	public static void main(String[] args) {
		System.out.println(isAlphaNum('7'));
		System.out.println(isSpecialChar('_', false));
		System.out.println(isPrefixChar('_'));
		System.out.println(isDomainChar('_'));
		System.out.println(singleAtSign("username@domain.com"));
		System.out.println(fetchBeforeAt("username@domain.com"));
		System.out.println(fetchAfterAt("username@domain.com"));
		System.out.println(isPrefix("you_!9850348me"));
		System.out.println(isDomain("gmail.com"));
		System.out.println(isDomain("g_ma-i-l.com"));

		
	}
	
	// isAlphaNum() to check if a character is alphanumeric
	public static boolean isAlphaNum(char character) {
		return Character.isLetterOrDigit(character);		
	}

	// isSpecialChar() to check if a character is an acceptable special character
	public static boolean isSpecialChar (char character, boolean bool) {
		if (character == '-' || character == '.') {
			return true;
		} else if  (character == '_' && bool){
			return true;
		} else 
			return false;
	}
	
	// isPrefixChar() to check if a character is a character allowed in the prefix
	public static boolean isPrefixChar(char character) {
		return isAlphaNum(character) || isSpecialChar(character,true);
	} 
	
	// isDomainChar() to check if a character is a character allowed in the prefix
	public static boolean isDomainChar(char character) {
		return isAlphaNum(character) || isSpecialChar(character,false);
	}
	
	//singleAtSign() to check if a String contain a single at sign
	public static boolean singleAtSign(String email) {
		int count=0;
		for ( int i = 0; i < email.length(); i++) {
			if (email.charAt(i)=='@') count++;			
		} 
		if (count==1) return true;
		else return false;
	}
	
	//fetchBeforeAt() to get the beginning of an email address
	public static String fetchBeforeAt(String email) {
		int i=0;
		for (i = 0; i < email.length(); i++) {
			if (email.charAt(i)=='@') {
				break;
			}			
		}
		return email.substring(0, i);	
	}
	
	//fetchAfterAt() to get the ending of an email address
	public static String fetchAfterAt(String email){
		int i=0;
		for (i = 0; i < email.length(); i++) {
			if (email.charAt(i)=='@') {
				break;
			}			
		}
		return email.substring(i+1, email.length());	
	}
	
	//isPrefix() to check if the start of a String is a valid email prefix
	public static boolean isPrefix(String prefix) {
		if (prefix.length()==0 || !isAlphaNum(prefix.charAt(0)) || !isAlphaNum(prefix.charAt(prefix.length()-1))) {
	        return false; // Empty prefix or first character is not alphanumeric
	    }
		
	    for (int i=1; i<prefix.length()-1; i++) {
	    	 if (!isPrefixChar(prefix.charAt(i))) {
	             return false; // Invalid character
	         }
	    	// An underscore, period, or dash must always be followed by at least one alphanumeric characters
	    	 if ( isSpecialChar(prefix.charAt(i),true) && !isAlphaNum(prefix.charAt(i + 1)) ) {
	                return false; 
	    	 }
	    }
		return true;
	}
	
	
	
	// isDomain()
	public static boolean isDomain(String input) {
		String[] parts = input.split("\\.");		
		// Made up of two portions separated by a period
		// Second portion contains at least two characters
		// First portion must start and end with an alphanumeric character
		if (parts.length != 2 || parts[0].length()==0 || parts[1].length() < 2 || !isAlphaNum(input.charAt(0)) || !isAlphaNum(parts[0].charAt(parts[0].length()-1))  ) {
			return false;
		}
			
		String part1 = parts[0], part2 = parts[1];
		int length1 = part1.length(), length2 = part2.length();
		
		// The second portion contains only letters of the alphabet
		int count=0;
		for (int i=0; i<length2; i++) {
			if (Character.isLetter(part2.charAt(i))) {
				count++;
			}
		}
		if (count != length2) {
		return false;
		}
		
		
		for (int i=1; i<length1-1; i++) {
			// First portion contains only alphanumeric characters, periods, and dashes.
			if ( !isDomainChar (part1.charAt(i))) {
				return false;
			}
			// A period or dash, in the first portion must be followed by one or more alphanumeric characters	
			if (isSpecialChar(part1.charAt(i), false) && !isAlphaNum(part1.charAt(i + 1))) {
			    return false;
			}
		}
		return true;
	}

	
	
	
}
