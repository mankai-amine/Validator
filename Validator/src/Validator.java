
// 2482903 Mohamed Amine Mankai   &  2457647 Iana Setrakova
import java.util.Arrays;

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
		System.out.println("email?" + isEmail("user.name@fake-m.ail.com"));
		System.out.println("username? "+isUsername("#sd99"));
		System.out.println("password? "+safePassword("H3l10-WoRld"));
		System.out.println(Arrays.toString(validEmails(new String[] {"a@gmail.com", "@yahoo.fr","b@msn.com"} )));
		System.out.println(Arrays.toString(validUsernames(new String[] {"-User2", ".cc123","userName"} )));
		System.out.println(Arrays.toString(validPasswords(new String[] {"H3l10-WoRld", "1nn3r-T1m3","W0w.Pr0ject"} )));
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
		// Contains at least one character
		// First and last characters are alphanumeric.
		if (prefix.length()==0 || !isAlphaNum(prefix.charAt(0)) || !isAlphaNum(prefix.charAt(prefix.length()-1))) {
	        return false; // Empty prefix or first character is not alphanumeric
	    }
		
	    for (int i=1; i<prefix.length()-1; i++) {
	    	// Contains only alphanumeric characters, underscores (_), periods (.), and dashes (-).
	    	 if (!isPrefixChar(prefix.charAt(i))) {
	             return false; 
	         }
	    	// An underscore, period, or dash must always be followed by at least one alphanumeric characters
	    	 if ( isSpecialChar(prefix.charAt(i),true) && !isAlphaNum(prefix.charAt(i + 1)) ) {
	                return false; 
	    	 }
	    }
		return true;
	}
	
	
	
	// isDomain()
	public static boolean isDomain(String domain) {
		
		// Determine the index of the last dot
		int lastIndex = domain.lastIndexOf('.');
		
		// The String has to contain a dot
		// First character has to be alphanumeric
		// Last character has to be a letter
        if (lastIndex == -1 || !isAlphaNum(domain.charAt(0))|| !Character.isLetter(domain.charAt(domain.length()-1))) {
        	return false;
        }
        
        // Split the string into two parts
        String part1 = domain.substring(0, lastIndex);
        String part2 = domain.substring(lastIndex + 1);
      
		int length1 = part1.length();
		int length2 = part2.length();
		
		// Second portion contains at least two characters
		// First portion must end with an alphanumeric character
		if ( length2 < 2 || !isAlphaNum(part1.charAt(length1-1))  ) {
			return false;
		}
					
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

	// isEmail()
	public static boolean isEmail (String email) {
		if (!singleAtSign(email)) {
			return false;
		}
		String prefix = fetchBeforeAt(email);
		String domain = fetchAfterAt(email);
		if (!isPrefix(prefix) || !isDomain(domain)) {
			return false;
		}
		return true;
	}
	
	
	 // isUsername()
	 public static String isUsername (String username) {
		 
		 // Contains seven or less characters
		 // Start with a period, or dash
		 // The last character is alphanumeric 
		 if (username.length()>7 || !isSpecialChar(username.charAt(0), false ) || !isAlphaNum(username.charAt(username.length()-1)) ) {
			 return "";
		 }
		 
		 for (int i=0; i<username.length()-1;i++) {
			 // Contains only alphanumeric characters, periods(.), or dashes(-)
			 if (!isDomainChar(username.charAt(i))) {
				 return "";
			 }
			 // A period, or dash must always be followed by at least one alphanumeric character
			 if ( !isAlphaNum(username.charAt(i)) && !isAlphaNum(username.charAt(i+1)) ) {
				 return "";
		     }
		 }
		 return username.toLowerCase();
	}
	 
	 // safePassword()
	 public static boolean safePassword(String password) {
		 
		 // Contains a minimum 7 characters and maximum 15 characters.
		 if (password.length()<7 || password.length()>15) {
			 return false;
		 }
		 int countAlphaNum=0, countLowerCase=0, countUpperCase=0, countNumber=0, countSpecialChar=0;
		 for (int i=0; i<password.length(); i++) {
			 
			 // The same character must never be repeated consecutively
			 if ( i<password.length()-1 && password.charAt(i)==password.charAt(i+1) ){
					 return false;
				 } 
			 
			 // Contains at least one alphanumeric characters
			 if (isAlphaNum(password.charAt(i))) {
				 countAlphaNum++;
			 }
			 
			 // /Contain at least one lower case letter
			 if (Character.isLowerCase(password.charAt(i))){
				 countLowerCase++;
			 }
			 
			 // Contain at least one upper case letter
			 if (Character.isUpperCase(password.charAt(i))){
				 countUpperCase++;
			 }
			 
			 // Contain at least one number
			 if (Character.isDigit(password.charAt(i))){
				 countNumber++;
			 }
			 
			 // Contain at least one period, dash or underscore
			 if (isSpecialChar(password.charAt(i), true)){
				 countSpecialChar++;
			 }
		 }
		 
		 return countAlphaNum!=0 && countLowerCase!=0 && countUpperCase!=0 && countNumber!=0 && countSpecialChar!=0; 
	 }
	 
	// validEmails()
	public static String[] validEmails (String[] emails) {
		int countValid=0; 
		for (int i=0; i<emails.length; i++) {
			 if (isEmail(emails[i])) {
				 countValid++;
			 } else {
				 emails[i]="";
			 }  
		 }
		
		String[] valEmails = new String[countValid];
		int index=0;
		for (int i=0; i<emails.length; i++) {
			if (!emails[i].equals("")) {
				valEmails[index]=emails[i];
				index++;
			}
		}
		return valEmails;
	}
	
	// validUsernames()
	public static String[] validUsernames(String[] usernames) {
		int countValid=0;		
		for(String username : usernames){
			if (!isUsername(username).equals("")) {
				countValid++;
			}
		}
		
		String[] valUsernames= new String[countValid];
		int index=0;
		for (int i=0;i<usernames.length;i++)  {
			if (!isUsername(usernames[i]).equals("")) {
				valUsernames[index]=usernames[i];
				index++;
			}
		}
		return valUsernames;
	}
		
	// validPasswords()
	public static String[] validPasswords (String[] passwords) {
		int countValid=0; 
		for (int i=0; i<passwords.length; i++) {
			 if (safePassword(passwords[i])) {
				 countValid++;
			 } else {
				 passwords[i]="";
			 }  
		 }
		
		String[] valPasswords = new String[countValid];
		int index=0;
		for (int i=0; i<passwords.length; i++) {
			if (!passwords[i].equals("")) {
				valPasswords[index]=passwords[i];
				index++;
			}
		}
		return valPasswords;
	}
	 
		
}
