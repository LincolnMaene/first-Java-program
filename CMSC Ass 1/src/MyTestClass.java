import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class MyTestClass {

	public static void main(String[] args) {
		
		File passText=new File ("C:\\Users\\LDM \\eclipse-workspace\\PasswordAssignment\\src\\Passwords.txt");
		
		Scanner fileReader;
		
		ArrayList <String> passWordList=new ArrayList<String>();
		
		try {
			
			fileReader = new Scanner(passText);
		
			while (fileReader.hasNextLine()) {
				
				passWordList.add(fileReader.nextLine());
			}
				
		
			PasswordCheckerUtility.invalidPasswords(passWordList);
			
		
		
		System.out.println(passWordList.toString());
		
		System.out.println(PasswordCheckerUtility.invalidPasswords(passWordList).toString());
		
		
		} catch (FileNotFoundException e) {
			
			System.out.println(e.getMessage());
		} 
		
	}

}
