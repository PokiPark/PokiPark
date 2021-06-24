package application;

import java.io.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.layout.*;

public class ZonesController extends Main {

	@FXML
	private AnchorPane rootPane;
	@FXML
	public void mainmenuClicked(ActionEvent event) throws IOException {
		changeStageTo(event, "MainMenu");
	}
}