package application;

import java.io.IOException;

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
}