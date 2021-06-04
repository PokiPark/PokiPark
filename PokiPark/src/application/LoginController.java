package application;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class LoginController {
	
	public LoginController() {
		
	}
	
	@FXML
	Label errorLabel;
	@FXML
	TextField username;
	@FXML
	PasswordField password;
	@FXML
	Button login;
	@FXML
	Hyperlink register;
	
	@FXML
	private void loginClicked(ActionEvent e) {
		if(username.getText().toString().equals("Hier müsste die Datenbank anbindung connecten.") && password.getText().toString().equals("hier auch.")) {
			//change scene to MainMenu
		}
		else {
			username.setText("");
			password.setText("");
			errorLabel.setText("Falsches Passwort oder Benutzername.");
		}
	}
	@FXML
	private void registerClicked(ActionEvent e) {
		
	}
	
}
