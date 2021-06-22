package application;

import java.io.*;
import java.net.*;
import java.util.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;

public class AdminMainMenuController extends Main implements Initializable {

	@FXML private ImageView avatar;
	
	@FXML private Label usernameLabel;

	@FXML public void avatarClicked(MouseEvent event) {
		System.out.println("Test");
		// Menu klappt sich aus mit >Einstellungen und >Statistik und so
	}

	@FXML public void pokedexClicked(ActionEvent event) throws IOException {
		changeStageTo(event, "AdminPokedex");
	}

	@FXML public void zonesClicked(ActionEvent event) throws IOException {
		changeStageTo(event, "AdminZones");
	}

	@FXML public void logoutClicked(ActionEvent event) throws IOException {
		changeStageTo(event, "Login");
	}
	
	@Override public void initialize(URL url, ResourceBundle rb) {
		usernameLabel.setText(Database.activeUser.getUsername() + " (Admin)");
		usernameLabel.setWrapText(true);
	}
}