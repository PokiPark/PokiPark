package application;

import java.io.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

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
	private void loginClicked(ActionEvent event) throws IOException {
		
		if(username.getText().toString().equals("Hier müsste die Datenbank anbindung connecten.") && password.getText().toString().equals("hier auch.")) {
			Node source = (Node) event.getSource();
			Stage oldStage = (Stage) source.getScene().getWindow();
			oldStage.close();
			
			//change scene to MainMenu
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Pokipark | MainMenu");
			stage.setScene(new Scene(root));
			stage.show();
		}
		else if(username.getText().isEmpty() && password.getText().isEmpty()) {
			errorLabel.setText("Bitte gebe deine Daten ein.");
		}
		else {
			errorLabel.setText("Falsches Passwort oder Benutzername.");
			password.clear();
		}
	}
	@FXML
	private void registerClicked(ActionEvent event) throws IOException {

		Node source = (Node) event.getSource();
		Stage oldStage = (Stage) source.getScene().getWindow();
		oldStage.close();
		
		//change scene to Register
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setTitle("Registrierung");
		stage.setScene(new Scene(root));
		stage.show();
	}
	
}
