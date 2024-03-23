
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
		System.out.println(isPrefix("you_me"));
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
		for ( int i = 0; i < email.length(); i++) {
			if (email.charAt(i)=='@') {
				temp=email.substring(0, i);
			}			
		}
		return temp;		
	}
	
	//fetchAfterAt() to get the ending of an email address
	public static String fetchAfterAt(String email){
		String temp="";
		for ( int i = 0; i < email.length(); i++) {
			if (email.charAt(i)=='@') {
				temp=email.substring(i+1, email.length());
			}			
		}
		return temp;	
	}
	
	//isPrefix() to check if the start of a String is a valid email prefix
	public static boolean isPrefix(String prefix) {
		if (prefix.length()==0 || !isAlphaNum(prefix.charAt(0))) {
	        return false; // Empty prefix or first character is not alphanumeric
	    }
		
	    for (int i=0; i<prefix.length(); i++) {
	    	 if (!isPrefixChar(prefix.charAt(i))) {
	             return false; // Invalid character
	         }
	         
	         if (isSpecialChar(prefix.charAt(i), true)) {
	             if ((i + 1)>=prefix.length() || !isAlphaNum(prefix.charAt(i + 1))) {
	                 return false; // Special character not followed by alphanumeric character
	             }
	         }
	    }
	    
	    return true; // All characters passed validation		
	}
	
	
}
