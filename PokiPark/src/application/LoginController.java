package application;

import java.io.*;
import java.sql.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class LoginController {

	@FXML
	private AnchorPane rootPane;
	@FXML
	private Label error_L;
	@FXML
	private TextField username_TF;
	@FXML
	private PasswordField password_PF;
	@FXML
	private Button login_B;
	@FXML
	private Hyperlink register_HL;

	@FXML
	private void login_B_Action(ActionEvent event) throws IOException, SQLException {
		Database.initData("usertable");
		Database.getUserlist().forEach((item) -> {
			if (username_TF.getText().toString().equals(item.getUsername())
					&& password_PF.getText().toString().equals(item.getPassword())) {
				item.setActiveUser();
				if (item.isAdmin()) {
					try {
						AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminMainMenu.fxml"));
						rootPane.getChildren().setAll(pane);
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
			} else if (username_TF.getText().isEmpty() && password_PF.getText().isEmpty()) {
				error_L.setText("Bitte gebe deine Daten ein.");
			} else {
				error_L.setText("Falsches Passwort oder Benutzername.");
			}
		});
		password_PF.clear();
	}

	@FXML
	private void register_HL_Action(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("Register.fxml"));
		rootPane.getChildren().setAll(pane);
	}
}