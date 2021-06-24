package application;

import java.io.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.layout.*;

public class ZonesController {

	@FXML
	private AnchorPane rootPane;
	@FXML
	public void mainmenuClicked(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		rootPane.getChildren().setAll(pane);
	}
}