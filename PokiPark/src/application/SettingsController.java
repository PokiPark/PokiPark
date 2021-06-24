package application;

import java.io.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SettingsController {

	@FXML
	private AnchorPane rootPane;
	
	@FXML
	private ListView<String> settings_LV;

	@FXML
	private void mainmenu_buttonAction(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		rootPane.getChildren().setAll(pane);
	}
}