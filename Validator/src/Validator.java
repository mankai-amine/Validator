
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
		System.out.println(isDomain(".x"));
		System.out.println("email?" + isEmail("user.name@fake-m.ail.com"));
		System.out.println("username? "+isUsername("#sd99"));
		System.out.println("password? "+safePassword("H3l10-WoRld"));
		System.out.println(Arrays.toString(validEmails(new String[] {"a@gmail.com", "@yahoo.fr","b@msn.com"} )));
		System.out.println(Arrays.toString(validUsernames(new String[] {"-User2", ".cc123","userName"} )));
		System.out.println(Arrays.toString(validPasswords(new String[] {"H3l10-WoRld", "1nn3r-T1m3","W0w.Pr0ject"} )));
	}
	
	
	// isAlphaNum() to check if a character is alphanumeric
	public static boolean isAlphaNum(char character) {
		// only accept a letter of the English alphabet (case insensitive) or a number between 0 and 9
		return Character.isLetterOrDigit(character);		
	}

	
	// isSpecialChar() to check if a character is an acceptable special character
	public static boolean isSpecialChar (char character, boolean bool) {
		// accept a dash or dot
		if (character == '-' || character == '.') {
			return true;
		// accept an underscore if the boolean is true
		} else if  (character == '_' && bool){
			return true;
		// doesn't accept anything else
		} else 
			return false;
	}
	
	
	// isPrefixChar() to check if a character is a character allowed in the prefix
	public static boolean isPrefixChar(char character) {
		// only accept alphanumeric characters, dashes, periods, or underscores
		return isAlphaNum(character) || isSpecialChar(character,true);
	} 
	
	
	// isDomainChar() to check if a character is a character allowed in the domain
	public static boolean isDomainChar(char character) {
		// only accept alphanumeric characters, dashes, or periods
		return isAlphaNum(character) || isSpecialChar(character,false);
	}
	
	
	//singleAtSign() to check if a String contain a single at sign
	public static boolean singleAtSign(String email) {
		int count=0;
		// check the number of @
		for ( int i = 0; i < email.length(); i++) {
			if (email.charAt(i)=='@') count++;			
		} 
		// check that there is only one @
		if (count==1) return true;
		else return false;
	}
	
	
	//fetchBeforeAt() to get the beginning of an email address
	public static String fetchBeforeAt(String email) {
		int i=0;
		// determine the index the the @
		for (i = 0; i < email.length(); i++) {
			if (email.charAt(i)=='@') {
				break;
			}			
		}
		// return the portion before @
		return email.substring(0, i);	
	}
	
	
	//fetchAfterAt() to get the ending of an email address
	public static String fetchAfterAt(String email){
		int i=0;
		// determine the index the the @
		for (i = 0; i < email.length(); i++) {
			if (email.charAt(i)=='@') {
				break;
			}			
		}
		// return the portion after @
		return email.substring(i+1, email.length());	
	}
	
	
	//isPrefix() to check if the start of a String is a valid email prefix
	public static boolean isPrefix(String prefix) {
		// Contains at least one character
		// First and last characters are alphanumeric.
		if (prefix.length()==0 || !isAlphaNum(prefix.charAt(0)) || !isAlphaNum(prefix.charAt(prefix.length()-1))) {
	        return false; 
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
	
	
	
	// isDomain()to check if the end of a String is a valid email domain
	public static boolean isDomain(String domain) {
		
		// Determine the index of the last dot
		int lastIndex = domain.lastIndexOf('.');
		
		// The String has to contain at least one dot
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
					
		/* Check if the second portion contains only letters (we already checked that 
		 * the last character is a letter, so the iteration can stop can i<length2-1)*/
		for (int i=0; i<length2-1; i++) {
			if (!Character.isLetter(part2.charAt(i))) {
				return false;
			}
		}
		
		for (int i=1; i<length1-1; i++) {
			// First portion contains only alphanumeric characters, periods, and dashes
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

	
	// isEmail() to check if a string is a valid email address
	public static boolean isEmail (String email) {
		// check if it contains exactly one @
		if (!singleAtSign(email)) {
			return false;
		}
		
		// determine the prefix and the domain
		String prefix = fetchBeforeAt(email);
		String domain = fetchAfterAt(email);
		
		// check if the prefix and the domain are valid
		if (!isPrefix(prefix) || !isDomain(domain)) {
			return false;
		}
		return true;
	}
	
	
	 // isUsername() to check if a string is a valid username
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
	 
	 
	 // safePassword() to check if a string is considered a safe password.
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
			 
			 // Number of alphanumeric characters
			 if (isAlphaNum(password.charAt(i))) {
				 countAlphaNum++;
			 }
			 
			 // Number of lower case letters
			 if (Character.isLowerCase(password.charAt(i))){
				 countLowerCase++;
			 }
			 
			 // Number of upper case letters
			 if (Character.isUpperCase(password.charAt(i))){
				 countUpperCase++;
			 }
			 
			 // Number of numbers
			 if (Character.isDigit(password.charAt(i))){
				 countNumber++;
			 }
			 
			 // Number of periods, dashes or underscores
			 if (isSpecialChar(password.charAt(i), true)){
				 countSpecialChar++;
			 }
		 }
		 
		 // Contain at least one upper case letter, one lower case letter, one number, and one period, dash or underscore
		 return countAlphaNum!=0 && countLowerCase!=0 && countUpperCase!=0 && countNumber!=0 && countSpecialChar!=0; 
	 }
	 
	 
	// validEmails(): Return array of Strings containing only the emails that are considered valid
	public static String[] validEmails (String[] emails) {
		 
		// determine the number of valid emails and replaces invalid ones by empty Strings
		int countValid=0;
		for (int i=0; i<emails.length; i++) {
			 if (isEmail(emails[i])) {
				 countValid++;
			 } else {
				 emails[i]="";
			 }  
		 }
		
		// create an array which length is equal to the number of valid emails
		String[] valEmails = new String[countValid];
		int index=0;
		
		// assign valid emails to the new array, and ignore empty Strings
		for (int i=0; i<emails.length; i++) {
			if (!emails[i].equals("")) {
				valEmails[index]=emails[i];
				index++;
			}
		}
		return valEmails;
	}
	
	
	// validUsernames() : Return array of Strings containing only the usernames that are considered valid
	public static String[] validUsernames(String[] usernames) {
				
		// determine the number of valid usernames
		int countValid=0;
		for(String username : usernames){
			if (!isUsername(username).equals("")) {
				countValid++;
			}
		}
		
		// create an array which length is equal to the number of valid usernames
		String[] valUsernames= new String[countValid];
		int index=0;
		
		// assign valid usernames to the new array, and ignore empty Strings
		for (int i=0;i<usernames.length;i++)  {
			if (!isUsername(usernames[i]).equals("")) {
				valUsernames[index]=usernames[i];
				index++;
			}
		}
		return valUsernames;
	}
		
	
	// validPasswords() : Return array of Strings containing only the passwords that are considered valid
	public static String[] validPasswords (String[] passwords) {
		
		// determine the number of valid passwords and replaces invalid ones by empty Strings
		int countValid=0; 
		for (int i=0; i<passwords.length; i++) {
			 if (safePassword(passwords[i])) {
				 countValid++;
			 } else {
				 passwords[i]="";
			 }  
		 }
		
		// create an array which length is equal to the number of valid passwords
		String[] valPasswords = new String[countValid];
		int index=0;
		
		// assign valid passwords to the new array, and ignore empty Strings
		for (int i=0; i<passwords.length; i++) {
			if (!passwords[i].equals("")) {
				valPasswords[index]=passwords[i];
				index++;
			}
		}
		return valPasswords;
	}
	 
		
}
