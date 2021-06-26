package application;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SettingsController implements Initializable {

	@FXML
	private AnchorPane rootPane;
	
	@FXML
	private AnchorPane rightPane;
	
	@FXML
	private ListView<String> settings_LV;

	private ObservableList<String> settings_OL = FXCollections.observableArrayList();
	
	@FXML
	private void mainmenu_buttonAction(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		rootPane.getChildren().setAll(pane);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		settings_OL.addAll("Allgemein", "Account");
	}
}