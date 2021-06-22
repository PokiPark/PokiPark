package application;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.regex.*;

import POJO.User;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class RegisterController extends Main {

	@FXML private Label errorLabel;
	@FXML private TextField username;
	@FXML private TextField email;

	@FXML private PasswordField password;
	@FXML private PasswordField passwordCheck;

	@FXML public void registerClicked(ActionEvent event) throws SQLException, IOException {
		if (registerDataIsValid(getUsername(), getEmail(), getPassword(), getPasswordCheck())) {
			Database.addToUserTable(getUsername(), getPassword(), getEmail());
			errorLabel.setText("");
			changeStageTo(event, "Login");
		}
	}

	@FXML public void loginClicked(ActionEvent event) throws IOException {
		changeStageTo(event, "Login");
	}

	private boolean registerDataIsValid(String username, String email, String password, String passwordCheck)
			throws SQLException {
		Database.initData("usertable");
		ArrayList<User> userlist = Database.getUserlist();
		if (username.isEmpty() || email.isEmpty() || password.isEmpty() || passwordCheck.isEmpty()) {
			errorLabel.setText("Gehe sicher, dass alle Felder ausgefüllt sind.");
			return false;
		} else if (!usernameIsValid(userlist, username)) {
			errorLabel.setText("Benutzername wird bereits verwendet.");
			return false;
		} else if (!emailIsValid(userlist, email)) {
			errorLabel.setText("Email wird bereits verwendet.");
			return false;
		} else if (!emailHasCorrectSemantics(email)) {
			errorLabel.setText("Email ist ungültig. Achte darauf, dass [@ und .] verwendet werden.");
			return false;
		} else if (!passwordIsValid(password)) {
			errorLabel.setText("Passwort benötigt min. 8 Zeichen; Groß- & Kleinbuchstaben, Zahlen und Sonderzeichen.");
			return false;
		} else if (!passwordMatchesWithPasswordCheck(password, passwordCheck)) {
			errorLabel.setText("Passwörter stimmen nicht überein.");
			return false;
		} else
			return true;
	}

	private boolean usernameIsValid(ArrayList<User> userlist, String username) {
		boolean usernameIsValid = true;

		for (int i = 0; i < userlist.size() & usernameIsValid; i++) {
			if (userlist.get(i).getUsername().equals(username))
				usernameIsValid = false;
		}
		return usernameIsValid;
	}

	private boolean emailIsValid(ArrayList<User> userlist, String email) {
		boolean emailIsValid = true;

		for (int i = 0; i < userlist.size() & emailIsValid; i++) {
			if (userlist.get(i).getEmail().equals(email))
				emailIsValid = false;
		}
		return emailIsValid;
	}

	private boolean emailHasCorrectSemantics(String email) {
		Pattern at = Pattern.compile("[@]");
		Pattern dot = Pattern.compile("[.]");
		Matcher hasAt = at.matcher(email);
		Matcher hasDot = dot.matcher(email);

		if (hasAt.find() & hasDot.find())
			return true;
		else
			return false;
	}

	private boolean passwordIsValid(String password) {
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