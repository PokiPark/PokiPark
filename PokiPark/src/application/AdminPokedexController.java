package application;

import java.io.*;

import javafx.event.*;
import javafx.fxml.*;

public class AdminPokedexController extends Main {

	@FXML
	public void mainmenuClicked(ActionEvent event) throws IOException {
		changeStageTo(event, "AdminMainMenu");
	}
}