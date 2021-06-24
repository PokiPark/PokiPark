package application;

import java.io.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.layout.*;

public class SettingsController extends Main {

	@FXML
	private AnchorPane rootPane;

	@FXML
	private void mainmenuClicked(ActionEvent event) throws IOException {
		changeStageTo(event, "Pokedex");
	}
}