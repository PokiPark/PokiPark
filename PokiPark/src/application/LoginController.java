package application;

import java.io.*;
import java.sql.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private AnchorPane rootPane;
	@FXML
	private Label errorLabel;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button login;
	@FXML
	private Hyperlink register;

	@FXML
	private void loginClicked(ActionEvent event) throws IOException, SQLException {
		Database.initData("usertable");
		Database.getUserlist().forEach((item) -> {
			if (username.getText().toString().equals(item.getUsername())
					&& password.getText().toString().equals(item.getPassword())) {
				item.setActiveUser();
				if (item.isAdmin()) {
					try {
						Node source = (Node) event.getSource();
						Stage oldStage = (Stage) source.getScene().getWindow();
						oldStage.close();

						FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("AdminMainMenu.fxml"));
						Parent root = (Parent) fxmlloader.load();
						Stage stage = new Stage();
						stage.setTitle("PokiPark | Admin-Hauptmenu");
						stage.setScene(new Scene(root));
						stage.show();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
						rootPane.getChildren().setAll(pane);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else if (username.getText().isEmpty() && password.getText().isEmpty()) {
				errorLabel.setText("Bitte gebe deine Daten ein.");
			} else {
				errorLabel.setText("Falsches Passwort oder Benutzername.");
			}
		});
		password.clear();
	}

	@FXML
	private void registerClicked(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("Register.fxml"));
		rootPane.getChildren().setAll(pane);
	}

	public String getUsername() {
		return username.getText().toString();
	}
}