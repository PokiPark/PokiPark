package application;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.regex.*;

import POJO.User;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

public class RegisterController {

	public RegisterController() {

	}

	final String dbUrl = "jdbc:mysql://localhost:3306/pokipark";
	final String dbUsername = "root";
	final String dbPassword = "";

	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;

	ArrayList<User> userlist = new ArrayList();

	@FXML
	private Label errorLabel;
	@FXML
	private TextField username;
	@FXML
	private TextField email;

	@FXML
	private PasswordField password;
	@FXML
	private PasswordField passwordCheck;

	@FXML
	public void registerClicked(ActionEvent event) throws SQLException, IOException {
		if (username.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty()
				|| passwordCheck.getText().isEmpty()) {
			errorLabel.setText("Gehe sicher, dass alle Felder ausgefüllt sind.");
		} else {
			if (passwordIsValid(getPassword())) {
				// check if password and passwordCheck are equal
				if (getPassword().equals(getPasswordCheck())) {
					Database.addUser(getUsername(), getPassword(), getEmail());
							errorLabel.setText("");
							try {
								changeToLogin(event);
							} catch (IOException e) {
								e.printStackTrace();
							}
				}
				else {
					errorLabel.setText("Passwörter stimmen nicht überein.");
				}
			} else {
				errorLabel.setText(
						"Passwort muss mindestens 8 Zeichen, Groß- und Kleinbuchstaben und Zahl oder Sonderzeichen haben.");
			}
		}
	}

	@FXML
	public void loginClicked(ActionEvent event) throws IOException {
		changeToLogin(event);
	}

	public boolean passwordIsValid(String password) {
		// @Author
		// https://stackoverflow.com/questions/1795402/check-if-a-string-contains-a-special-character
		if (password.length() > 7) {

			Pattern letters = Pattern.compile("[a-zA-Z]");
			Pattern numbers = Pattern.compile("[0-9]");
			Pattern specials = Pattern.compile("[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]");

			Matcher hasLetters = letters.matcher(password);
			Matcher hasNumbers = numbers.matcher(password);
			Matcher hasSpecials = specials.matcher(password);

			return hasLetters.find() && hasNumbers.find() && hasSpecials.find();
		} else {
			return false;
		}
	}

	public void changeToLogin(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
		Stage oldStage = (Stage) source.getScene().getWindow();
		oldStage.close();

		// change scene to Login

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setTitle("Login");
		stage.setScene(new Scene(root));
		stage.show();
	}

	public String getUsername() {
		return username.getText().toString();
	}

	public String getEmail() {
		return email.getText().toString();
	}

	public String getPassword() {
		return password.getText().toString();
	}

	public String getPasswordCheck() {
		return passwordCheck.getText().toString();
	}
}