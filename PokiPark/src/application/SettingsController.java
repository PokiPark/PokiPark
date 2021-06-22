package application;

import java.io.*;

import javafx.event.*;
import javafx.fxml.*;

public class SettingsController extends Main {
	
	@FXML private void mainmenuClicked(ActionEvent event) throws IOException {
		changeStageTo(event, "MainMenu");
	}
}