/**
 * Student Junit Tests
 * @author Lincoln Maene
 */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Lincoln Maene
 *
 */
public class PasswordCheckerTest_STUDENT {
	
	ArrayList<String> passwords;
	String password1, password2;


	@Before
	public void setUp() throws Exception {
		
		passwords = new ArrayList<String>();	
		
	}

	@After
	public void tearDown() throws Exception {
		
		passwords = null;
	
	}

	/**
	 * Test if the password is less than 8 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			PasswordCheckerUtility.isValidPassword("PWD1");
		} catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException
				| InvalidSequenceException e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "The password must be at least 6 characters long.");
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
			PasswordCheckerUtility.isValidPassword("aaaaaaaa");
		} catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException
				| InvalidSequenceException e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "The password must contain at least one uppercase alphabetic character.");
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			PasswordCheckerUtility.isValidPassword("AAAAAAAA");
		} catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException
				| InvalidSequenceException e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "The password must contain at least one lowercase alphabetic character.");
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		
			
				assertTrue(PasswordCheckerUtility.isWeakPassword("AAa123"));
			
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			PasswordCheckerUtility.isValidPassword("AAa123ggg");
		} catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException
				| InvalidSequenceException e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "The password cannot contain more than two of the same character in sequence.");
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			PasswordCheckerUtility.isValidPassword("AAagtrggg");
		} catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException
				| InvalidSequenceException e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "The password must contain at least one digit.");
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("Abc123Abc"));
		} catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException
				| InvalidSequenceException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	
	/**
	 * Test the validPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testValidPasswords() {
		
	
		passwords.add("Abc123Abc");//add valid password and three invalid ones
		passwords.add("AAagtrggg");
		passwords.add("AAAAAAAA");
		passwords.add("AAa123ggg");
		
		
		passwords=PasswordCheckerUtility.invalidPasswords(passwords);
		
		
		
		assertTrue(passwords.contains("AAagtrggg"));//check only invalid passwords are left
		assertTrue(passwords.contains("AAAAAAAA"));
		assertTrue(passwords.contains("AAagtrggg"));
		assertFalse(passwords.contains("Abc123Abc"));
	}


		
	
}
