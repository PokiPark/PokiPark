package application;

import java.io.*;
import java.util.regex.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RegisterController {

	public RegisterController() {
		
	}
	
	@FXML
	private Label errorLabel;
	@FXML
	private TextField username;
	@FXML
	private TextField email;
	@FXML
	private PasswordField password;
	@FXML
	private PasswordField passwordCheck;
	@FXML
	public void registerClicked(ActionEvent event) {
		if(username.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty() || passwordCheck.getText().isEmpty()) {
			errorLabel.setText("Gehe sicher, dass alle Felder ausgefüllt sind.");
		}
		else {
			if(passwordIsValid(password.getText().toString())) {
				// check if password and passwordCheck are equal
				if (password.getText().toString().equals(passwordCheck.getText().toString())) {
					errorLabel.setText("");
					// change scene to login? (oder MainMenu?) (oder else?)
					// am besten wahrscheinlich email-verifikations-screen -> wenn verifiziert gehen Daten in die Datenbank über + Scenechange zu Loginscreen
				}
				
				else {
					errorLabel.setText("Passwörter stimmen nicht überein.");
				}
			}
			else {
				errorLabel.setText("Passwort muss mindestens 8 Zeichen, Groß- und Kleinbuchstaben und Zahl oder Sonderzeichen haben.");
			}
		}
	}
	@FXML
	public void loginClicked(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
		Stage oldStage = (Stage) source.getScene().getWindow();
		oldStage.close();
		
		//change scene to Login
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setTitle("Login");
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public boolean passwordIsValid(String password) {
		//@Author https://stackoverflow.com/questions/1795402/check-if-a-string-contains-a-special-character
		if(password.length() > 7) {

			Pattern letters = Pattern.compile("[a-zA-Z]");
			Pattern numbers = Pattern.compile("[0-9]");
			Pattern specials = Pattern.compile("[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]");
			
			Matcher hasLetters = letters.matcher(password);
			Matcher hasNumbers = numbers.matcher(password);
			Matcher hasSpecials = specials.matcher(password);
			
			return hasLetters.find() && hasNumbers.find() && hasSpecials.find();
		}
		else {
			return false;
		}
	}
}