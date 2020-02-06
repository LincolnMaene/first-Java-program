/**
 * Utility Class of the Password Checker
 * @author Lincoln Maene
 */

import java.util.ArrayList;


public class PasswordCheckerUtility { //method checks validity of password
	
	/**
	 * 
	 * @param passwordString is the passed password
	 * @return true if the password is valid
	 * @throws LengthException returned if the length is too short
	 * @throws NoUpperAlphaException thrown in abscence of uppercase characters
	 * @throws NoLowerAlphaException thrown in the abscence of lowercase characters
	 * @throws NoDigitException thrown in the abscence of digits
	 * @throws InvalidSequenceException thrown in the presence of three repeated characters
	 */
	public static boolean isValidPassword(java.lang.String passwordString) 
	throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, InvalidSequenceException
	{
		
		char passwordChar[];//character array to hold password characters
		
		boolean isRepeatedChar=false;//boolean to know if characters are repeated
		
		if (passwordString.length()<6)//check pass for length
		{
			throw new LengthException ("The password must be at least 6 characters long.");
		}
		
		else if (passwordString.equals(passwordString.toLowerCase()))//check presence of uppercase
		{
			throw new NoUpperAlphaException ("The password must contain at least one uppercase alphabetic character.");
		}
		
		else if (passwordString.equals(passwordString.toUpperCase()))//check presence of lowercase
		{
			throw new NoLowerAlphaException ("The password must contain at least one lowercase alphabetic character.");
		}
		
		else if (!passwordString.matches(".*[0-9].*"))//check presence of digits
		{
			throw new NoDigitException("The password must contain at least one digit.");
		}
		
		passwordChar=new char[passwordString.length()];//create array of password characters
		
		passwordChar=passwordString.toCharArray();
		
		try {
		
		for (int i=1; i<passwordChar.length;i++)//find repeated password character and stop
		{
			if (passwordChar[i]==passwordChar[i-1] && passwordChar[i]==passwordChar[i+1])
			{
				isRepeatedChar=true;
				break;
			}
		} 
		}catch (ArrayIndexOutOfBoundsException out) {
			
			out.getMessage();
		}
		
		if (isRepeatedChar)//check if characters are repeated thrice
			
			throw new InvalidSequenceException ("The password cannot contain more than two of the same character in sequence.");
		
		
		
		return true;
		
	}
	
	/**
	 * Checks if the password is less than 9 characters
	 * @param passwordString is the password 
	 * @return true if the password is weak (less than 10 characters)
	 */
	public static boolean isWeakPassword(java.lang.String passwordString){
	
		if (passwordString.length()>=6 && passwordString.length()<=9)
			
			return true;
		
		return false;
		
	}
	
	/**
	 * Takes an arraylist of passwords and finds all invalid ones
	 * @param passwords is the list of passwords to be checked
	 * @return an arraylist of invalid passwords
	 */
	public static ArrayList<String> invalidPasswords(ArrayList<String> passwords){
	
		
		ArrayList <String> invalidPass=new ArrayList<String>(passwords);//arraylist to hold invalid passwords
				
		
		for (String element: passwords) {//iterate through original array and remove invalid ones in the copy arraylist
			
			try {
				if (isValidPassword(element)) {
						
					invalidPass.remove(element);
				}
			} catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException
					| InvalidSequenceException e) {//cath errors
				
				e.getMessage();
			}
		}
		
		return invalidPass;//return arraylist of invalid passwords
		
	}

}
