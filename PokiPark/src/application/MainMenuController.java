package application;

import java.io.*;
import java.net.*;
import java.util.*;

import javafx.application.Platform;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class MainMenuController implements Initializable {

	@FXML
	private AnchorPane rootPane;

	@FXML
	private Label avatar_L, username_L;

	private ContextMenu cm = new ContextMenu();
	private MenuItem settings_MI = new MenuItem("Einstellungen"), logout_MI = new MenuItem("Ausloggen"),
			quit_MI = new MenuItem("Verlassen");

	@FXML
	public void pokedex_B_Action(ActionEvent event) throws IOException {
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("Pokedex.fxml"));
		rootPane.getChildren().setAll(pane);
	}

	@FXML
	public void zones_B_Action(ActionEvent event) throws IOException {
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("Zones.fxml"));
		rootPane.getChildren().setAll(pane);
	}

	@FXML
	public void logout_B_Action(ActionEvent event) throws IOException {
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
		rootPane.getChildren().setAll(pane);
		Database.setActiveUser(null);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		username_L.setText(Database.activeUser.getUsername());
		username_L.setWrapText(true);

		settings_MI.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				try {
					AnchorPane pane = FXMLLoader.load(getClass().getResource("Settings.fxml"));
					rootPane.getChildren().setAll(pane);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		logout_MI.setOnAction(new EventHandler<ActionEvent>() {
			
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
		
		quit_MI.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});

		cm.getItems().addAll(settings_MI, logout_MI, quit_MI);
		avatar_L.setContextMenu(cm);
		ImageView iv = new ImageView(new Image("Avatar.png"));
		iv.setFitHeight(60);
		iv.setFitWidth(60);
		avatar_L.setGraphic(iv);
	}
}