package application;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class RegisterController {

	public RegisterController() {
		
	}
	
	@FXML
	private TextField username;
	@FXML
	private TextField email;
	@FXML
	private PasswordField password;
	@FXML
	private PasswordField passwordCheck;
	
	@FXML
	public void registerClicked(ActionEvent e) {
		//change scene to login? (oder MainMenu?) (oder else?)
	}
	@FXML
	public void loginClicked(ActionEvent e) {
		//change scene to Login
	}
}