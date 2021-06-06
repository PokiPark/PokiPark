package application;

import java.io.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.stage.*;

public class MainMenuController {

	public MainMenuController() {
		
	}
	
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
		Node source = (Node) event.getSource();
		Stage oldStage = (Stage) source.getScene().getWindow();
		oldStage.close();
		
		//change scene to Pokedex
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Pokedex.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setTitle("Pokipark | Pokedex");
		stage.setScene(new Scene(root));
		stage.show();
	}
	@FXML
	public void zonesClicked(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
		Stage oldStage = (Stage) source.getScene().getWindow();
		oldStage.close();
		
		//change scene to Zones
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Zones.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setTitle("Pokipark | Zones");
		stage.setScene(new Scene(root));
		stage.show();
	}
	@FXML
	public void logoutClicked(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
		Stage oldStage = (Stage) source.getScene().getWindow();
		oldStage.close();
		
		//change scene to Login
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setTitle("Login");
		stage.setScene(new Scene(root));
		stage.show();
	}
}
