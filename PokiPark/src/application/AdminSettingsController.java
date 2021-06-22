package application;

import java.io.*;

import javafx.event.*;
import javafx.fxml.*;

public class AdminSettingsController extends Main {
	
	@FXML private void mainmenuClicked(ActionEvent event) throws IOException {
		changeStageTo(event, "MainMenu");
	}
}