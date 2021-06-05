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
	private TextField username;
	@FXML
	private TextField email;
	@FXML
	private PasswordField password;
	@FXML
	private PasswordField passwordCheck;
	
	@FXML
	public void registerClicked(ActionEvent event) {
		//change scene to login? (oder MainMenu?) (oder else?)
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