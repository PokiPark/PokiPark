package application;

import java.io.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

public class PokedexController {

	public PokedexController() {
		
	}
	
	@FXML
	public void mainmenuClicked(ActionEvent event) throws IOException {
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
}
