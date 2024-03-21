
public class Validator {

	public static void main(String[] args) {
		System.out.println(isAlphaNum('7'));
		System.out.println(isSpecialChar('-', false));
	}
	
	// isAlphaNum()
	public static boolean isAlphaNum(char character) {
		return Character.isLetterOrDigit(character);		
	}

	// isSpecialChar()
	public static boolean isSpecialChar (char character, boolean bool) {
		if (character == '-' || character == '.') {
			return true;
		} else if  (character == '_' && bool){
			return true;
		} else 
			return false;
	}
	
}