
public class Validator {

	public static void main(String[] args) {
		System.out.println(isAlphaNum('7'));
		
	}
	
	public static boolean isAlphaNum(char character) {
		return Character.isLetterOrDigit(character);		
	}

	
	
}
