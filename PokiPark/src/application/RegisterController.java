package application;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.regex.*;

import POJO.User;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class RegisterController {

	@FXML
	private AnchorPane rootPane;

	@FXML
	private Label error_L;

	@FXML
	private TextField username_TF, email_TF;

	@FXML
	private PasswordField password_PF, passwordCheck_PF;

	// START OF FXML-METHODS //

	@FXML
	public void register_B_Action(ActionEvent event) throws SQLException, IOException {
		if (registerDataIsValid(getUsername(), getEmail(), getPassword(), getPasswordCheck())) {
			Database.sendSqlCommand("INSERT INTO usertable (username, password, email, id, admin) VALUES ('"
					+ getUsername() + "', '" + getPassword() + "','" + getEmail() + "', NULL, 0);");
			error_L.setText("");
			AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
			rootPane.getChildren().setAll(pane);
		}
	}

	@FXML
	public void login_HL_Action(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
		rootPane.getChildren().setAll(pane);
	}

	// END OF FXML-METHODS //
	
	private boolean registerDataIsValid(String username, String email, String password, String passwordCheck)
			throws SQLException {

		Database.initData("usertable");
		ArrayList<User> userlist = Database.getUserlist();

		if (username.isEmpty() || email.isEmpty() || password.isEmpty() || passwordCheck.isEmpty()) {
			error_L.setText("Gehe sicher, dass alle Felder ausgefüllt sind.");
			return false;

		} else if (!usernameIsValid(userlist, username)) {
			error_L.setText("Benutzername wird bereits verwendet.");
			return false;

		} else if (!emailIsValid(userlist, email)) {
			error_L.setText("Email wird bereits verwendet.");
			return false;

		} else if (!emailHasCorrectSemantics(email)) {
			error_L.setText("Email ist ungültig. Achte darauf, dass [@ und .] verwendet werden.");
			return false;

		} else if (!passwordIsValid(password)) {
			error_L.setText("Passwort benötigt min. 8 Zeichen; Groß- & Kleinbuchstaben, Zahlen und Sonderzeichen.");
			return false;

		} else if (!passwordMatchesWithPasswordCheck(password, passwordCheck)) {
			error_L.setText("Passwörter stimmen nicht überein.");
			return false;

		} else
			return true;
	}

	public boolean usernameIsValid(ArrayList<User> userlist, String username) {
		boolean usernameIsValid = true;

		for (int i = 0; i < userlist.size() & usernameIsValid; i++) {

			if (userlist.get(i).getUsername().equals(username))
				usernameIsValid = false;
		}
		return usernameIsValid;
	}

	public boolean emailIsValid(ArrayList<User> userlist, String email) {
		boolean emailIsValid = true;

		for (int i = 0; i < userlist.size() & emailIsValid; i++) {

			if (userlist.get(i).getEmail().equals(email))
				emailIsValid = false;
		}
		return emailIsValid;
	}

	public boolean emailHasCorrectSemantics(String email) {
		Pattern at = Pattern.compile("[@]");
		Pattern dot = Pattern.compile("[.]");
		Matcher hasAt = at.matcher(email);
		Matcher hasDot = dot.matcher(email);

		if (hasAt.find() & hasDot.find())
			return true;
		else
			return false;
	}

	public boolean passwordIsValid(String password) {
		// @Author
		// https://stackoverflow.com/questions/1795402/check-if-a-string-contains-a-special-character
		if (password.length() >= 8) {

			Pattern letters = Pattern.compile("[a-zA-Z]");
			Pattern numbers = Pattern.compile("[0-9]");
			Pattern specials = Pattern.compile("[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]");

			Matcher hasLetters = letters.matcher(password);
			Matcher hasNumbers = numbers.matcher(password);
			Matcher hasSpecials = specials.matcher(password);

			return hasLetters.find() && hasNumbers.find() && hasSpecials.find();

		} else
			return false;
	}

	private boolean passwordMatchesWithPasswordCheck(String password, String passwordCheck) {
		if (password.equals(passwordCheck))
			return true;

		else
			return false;
	}

	public String getUsername() {
		return username_TF.getText().toString();
	}

	public String getEmail() {
		return email_TF.getText().toString();
	}

	public String getPassword() {
		return password_PF.getText().toString();
	}

	public String getPasswordCheck() {
		return passwordCheck_PF.getText().toString();
	}
}