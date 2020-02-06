/**
 * Gui for Password Checker
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PasswordCheckerGui extends Application{
	
	public static void main (String[]args) {
		
 		launch(args);
	}
	
	@Override
	public void start (Stage primaryStage) {
		
		primaryStage.setTitle("Password Checker");
		
		Button checkPass=new Button();//add buttons to primary stage
		Button checkFile=new Button();
		Button exit=new Button();
		
		checkPass.setMnemonicParsing(true);//set mnenomic to check pass button
		checkFile.setMnemonicParsing(true);//set mnenomic to check File button
		exit.setMnemonicParsing(true);//set mnenomic to exit button

		FileChooser filechooser=new FileChooser();// create new file chooser
		filechooser.setTitle("Password File Finder");//create File Finder
		
		checkPass.setText("Check _Password");//set texts to buttons
		checkFile.setText("Check Passwords in _File");
		exit.setText("_Exit");
		
		GridPane grid=new GridPane();//create gridPane
		
		grid.setAlignment(Pos.BASELINE_CENTER);//SET Gridpane at upper left
		
		grid.setHgap(10);//set spacing between grids
		grid.setVgap(10);
		
		grid.setPadding(new Insets(25, 25, 25, 25));//set spaces around edges of grids
		
		HBox buttonBox=new HBox();//Create horizontal box of buttons
		HBox passBox=new HBox();//horizontal box to hold passwords
		HBox repassBox=new HBox();//horizontal box to hold passwords

		
		buttonBox.setSpacing(20);//set spacing for butttons 
		buttonBox.setAlignment(Pos.CENTER);
		
		passBox.setSpacing(20);
		repassBox.setSpacing(20);
		
		Tooltip checkPassTip=new Tooltip();//create tooltip for check pass button
		Tooltip checkPassFile=new Tooltip();//create tooltip for check pass button
		Tooltip exitTip=new Tooltip();//create new tooltip for exit button

		checkPassFile.setText("Check a list of passwords on file\n(Alt-F)");//set text to checkfile tooltip
		checkPassTip.setText("Check that your password is valid\n(Alt-P)");//set text to tooltip
		exitTip.setText("Exit the password checker\n(Alt-E)");//exit password checker
		
		checkPass.setTooltip(checkPassTip);//set tooltip to button
		checkFile.setTooltip(checkPassFile);//set tooltip to button for checking file
		exit.setTooltip(exitTip);//set tooltip for exit button
		
				
		

		
		buttonBox.getChildren().addAll(checkPass, checkFile, exit);//add buttons 

		
		
			
		
		
		primaryStage.setScene(new Scene (grid, 700, 480));//set dimensions of scene
		
		Text instructions=new Text("Use the following rules while creating your password\n"+//display instructions
								   "    1.Length must be at least 6 characters; a strong password will contain at least 10 characters\n"+
								   "    2.Must contain at least one upper case alpha character\n"+
								   "    3.Must contain at least one lower case alpha character\n"+
								   "    4.Must contain at least one numeric character\n"+
								   "    5.May not have more than 2 of the same character in sequence");
		grid.add(instructions, 0,0 );//add instructions to grid
		
		Label password=new Label("			Password"); //add password label
		
		TextField passwordField=new TextField();//create textfield for password
		
		
		passBox.getChildren().addAll(password,passwordField);
		
		grid.add(passBox, 0, 3);//add password box to grid
		
		Label rePassword=new Label("			Re-type\n"+
								   "			Password"); //add password label

		
		TextField rePasswordField=new TextField();//create textfield for password

		
		repassBox.getChildren().addAll(rePassword,rePasswordField);
		
		grid.add(repassBox, 0, 7);//add repassBox to the grid
		
		grid.add(buttonBox, 0, 15);
		
		
		checkPass.setOnAction(new EventHandler<ActionEvent>() {//action on checkPassword
			
			@Override
			public void handle(ActionEvent e) {
				

				String pass1=passwordField.getText();//get input from first password field
				
				String pass2=rePasswordField.getText();//get input from second password field
				
				try {
				
				if (!pass1.contentEquals(pass2)) {//display error message if passwords do not match
					
					throw new UnmatchedException("The passwords do not match");
					
					}
				} catch (UnmatchedException e3) {
					
					JOptionPane.showMessageDialog(null, e3.getMessage(),"Password Status", JOptionPane.ERROR_MESSAGE);
					
					return;//stop execution of method 

				}
				
				
			
				
				try {
					if (PasswordCheckerUtility.isWeakPassword(pass1)){  //check for invalid password
						
						JOptionPane.showMessageDialog(null, "The password is valid but weak","Password Status", JOptionPane.INFORMATION_MESSAGE);

					}
					
					else if (PasswordCheckerUtility.isValidPassword(pass1)){
						
						JOptionPane.showMessageDialog(null, "The password is valid","Password Status", JOptionPane.INFORMATION_MESSAGE);
					}

				} catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException
						| InvalidSequenceException e1) {
					// Print error message
					
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Password Error", JOptionPane.ERROR_MESSAGE);
				}
			
			}
		});
		
		checkFile.setOnAction(new EventHandler<ActionEvent>() {//action on check password in file button
			@Override
			public void handle(ActionEvent e) {
				
				ArrayList<String> passwordList=new ArrayList<>();
				
				File passwordFile=filechooser.showOpenDialog(primaryStage);//display file chooser
 				Scanner fileReader=null;//create fileReader
				
				try {
					
					fileReader=new Scanner(passwordFile);//initialize new fileReader
					
					while (fileReader.hasNext()) {//read from list of passwords
						
						passwordList.add(fileReader.nextLine());
					}
					
				
					passwordList=PasswordCheckerUtility.invalidPasswords(passwordList);//create arraylist of invalid passwords
					
					String errorMessage="";
					
					for (String element: passwordList) {
						
						errorMessage+=element+" ";//create String to hold errorMessage
						
						try {
							PasswordCheckerUtility.isValidPassword(element);
						} catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException
								| InvalidSequenceException e1) {
							// add erromessage to error string
							errorMessage+=e1.getMessage();
						}
						
						errorMessage+="\n";
						
					}
					
					
					JOptionPane.showMessageDialog(null, errorMessage,"Password Error", JOptionPane.ERROR_MESSAGE);
					
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(),"File Error", JOptionPane.ERROR_MESSAGE);
				} 

				fileReader.close();
			}
		});
		
		


		primaryStage.show();//show stage
		
		
		exit.setOnAction(new EventHandler<ActionEvent>() {//action for the exit button
			
			@Override
			public void handle(ActionEvent e) {
				
				Platform.exit();
			}
		});
		

	}

}
