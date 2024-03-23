
//your student ID? Mankai, Mohamed Amine   &  2457647 Iana Setrakova

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
		System.out.println(isDomain("833guiif_34"));
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
		String temp="";
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
		if (prefix.length()==0 || !isAlphaNum(prefix.charAt(0)) || isAlphaNum(prefix.charAt(prefix.length()-1))) {
	        return false; // Empty prefix or first character is not alphanumeric
	    }
		
	    for (int i=1; i<prefix.length()-1; i++) {
	    	 if (!isPrefixChar(prefix.charAt(i))) {
	             return false; // Invalid character
	         }
	         
	         if (isSpecialChar(prefix.charAt(i), true)) {
	             if (!isNextCharacter(i, prefix)) {
	                 return false; // Special character not followed by alphanumeric character
	             }
	         }
	    }	    
	    return true; // All characters passed validation		
	}
	
	public static boolean isNextCharacter(int i, String str) {
		return ((i + 1)<str.length() && isAlphaNum(str.charAt(i + 1)));
	}
	
	public static boolean isDomain(String domain) {	
		if (domain.length() < 3) {
			//domain should have at least two portions separated by a period, that is 3 characters
			return false; 
		}
		int dot=domain.indexOf('.');//index of period character in a string domain 
		if (dot<=0 || dot==domain.length()-1) {
			// indexOf returns -1 when the character is not found
			// if indexOf return 0, the first character in string is "."
			return false;
		}
		String firstPortion=domain.substring(0,dot);
		String secondPortion=domain.substring(dot+1);
		
		//check the first portion	
		if (!isAlphaNum(firstPortion.charAt(0))){
			return false; // First portion must start with an alphanumeric character
		}
		
		for (int i = 0; i < firstPortion.length(); i++) {
	        char currentChar = firstPortion.charAt(i);
	        if (!isDomainChar(currentChar)) {
	            return false; // Invalid character in the first portion
	        }

	        if (isSpecialChar(currentChar, true)) {
	            if (isNextCharacter(i,firstPortion)) {
	                return false; // Period or dash in the first portion must be followed by one or more alphanumeric characters
	            }
	        }
	    }

	    // Check second portion
	    if (secondPortion.length() < 2 ) {
	        return false; // Second portion must have at least two characters and contain only letters of the alphabet
	    } else {
	    	for (int i = 0; i < secondPortion.length(); i++) {
	    		if (!isAlphaNum(secondPortion.charAt(i))) return false;
	    	}
	    }
		
		return true;
	}
	
	
	
}
