package application;

import java.io.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.layout.*;

public class AdminZonesController extends Main {

	@FXML
	private AnchorPane rootPane;
	@FXML
	public void mainmenuClicked(ActionEvent event) throws IOException {
		changeStageTo(event, "AdminMainMenu");	}
}