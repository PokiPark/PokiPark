package application;

import java.io.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

public class MainMenuController {

	public MainMenuController() {
		
	}
	
	@FXML
	public void logoutClicked(ActionEvent event) throws IOException {
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
