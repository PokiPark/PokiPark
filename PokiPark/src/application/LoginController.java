package application;

import java.io.*;
import java.sql.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class LoginController extends Main {

	@FXML Label errorLabel;
	@FXML TextField username;
	@FXML PasswordField password;
	@FXML Button login;
	@FXML Hyperlink register;

	@FXML private void loginClicked(ActionEvent event) throws IOException, SQLException {
		Database.initData("usertable");
		Database.getUserlist().forEach((item) -> {
			// System.out.println(Database.getUserlist());
			if (username.getText().toString().equals(item.getUsername())
					&& password.getText().toString().equals(item.getPassword())) {
				item.isActiveUser();
				if (item.isAdmin()) {
					try {
						changeStageTo(event, "AdminMainMenu");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						changeStageTo(event, "MainMenu");
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

	@FXML private void registerClicked(ActionEvent event) throws IOException {
		changeStageTo(event, "Register");
	}

	public String getUsername() {
		return username.getText().toString();
	}
}