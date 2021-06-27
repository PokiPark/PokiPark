package application;

import java.io.*;
import java.net.*;
import java.util.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class AdminMainMenuController implements Initializable {

	@FXML
	private AnchorPane rootPane;
	@FXML
	private Label avatar;
	@FXML
	private Label usernameLabel;
	
	private ContextMenu cm = new ContextMenu();
	private MenuItem settings_MI = new MenuItem("Einstellungen");
	private MenuItem logout_MI = new MenuItem("Ausloggen");
	private MenuItem quit_MI = new MenuItem("Verlassen");

	@FXML
	public void pokedexClicked(ActionEvent event) throws IOException {
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminPokedex.fxml"));
		rootPane.getChildren().setAll(pane);
	}

	@FXML
	public void zonesClicked(ActionEvent event) throws IOException {
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminZones.fxml"));
		rootPane.getChildren().setAll(pane);
	}

	@FXML
	public void logoutClicked(ActionEvent event) throws IOException {
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
		rootPane.getChildren().setAll(pane);
		Database.setActiveUser(null);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		usernameLabel.setText(Database.activeUser.getUsername());
		usernameLabel.setWrapText(true);
		
		settings_MI.setOnAction(new EventHandler<ActionEvent>( ) {
			@Override
			public void handle(ActionEvent event) {
				try {
					AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminSettings.fxml"));
					rootPane.getChildren().setAll(pane);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		logout_MI.setOnAction(new EventHandler<ActionEvent>( ) {
			@Override
			public void handle(ActionEvent event) {
				try {
					AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
					rootPane.getChildren().setAll(pane);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		quit_MI.setOnAction(new EventHandler<ActionEvent>( ) {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		
		cm.getItems().addAll(settings_MI, logout_MI, quit_MI);
		avatar.setContextMenu(cm);
		ImageView iv = new ImageView(new Image("Avatar.png"));
		iv.setFitHeight(60);
		iv.setFitWidth(60);
		avatar.setGraphic(iv);
	}
}