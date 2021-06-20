package application;

import java.io.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;

public class MainMenuController extends Main {
	
	@FXML
	private ImageView avatar;
	@FXML
	private Label usernameLabel;
	@FXML
	public void avatarClicked(MouseEvent event) {
		System.out.println("Test");
		// Menu klappt sich aus mit >Einstellungen und >Statistik und so
	}
	@FXML
	public void pokedexClicked(ActionEvent event) throws IOException {
		changeStageTo(event, "Pokedex");
	}
	@FXML
	public void zonesClicked(ActionEvent event) throws IOException {
		changeStageTo(event, "Zones");
	}
	@FXML
	public void logoutClicked(ActionEvent event) throws IOException {
		changeStageTo(event, "Login");
	}
}