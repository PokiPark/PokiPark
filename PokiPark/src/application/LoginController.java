package application;

import java.io.*;
import java.sql.*;
import java.util.*;

import POJO.User;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

public class LoginController {
	
	public LoginController() {
		
	}
	
	final String dbUrl = "jdbc:mysql://localhost:3306/pokipark";
	final String dbUsername = "root";
	final String dbPassword = "";
	
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	ArrayList<User> userlist = new ArrayList();
	
	@FXML
	Label errorLabel;
	@FXML
	TextField username;
	@FXML
	PasswordField password;
	@FXML
	Button login;
	@FXML
	Hyperlink register;
	@FXML
	private void loginClicked(ActionEvent event) throws IOException, SQLException {
		connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		statement = connection.createStatement();
		resultSet = statement.executeQuery("SELECT * FROM userbank");
		
		while(resultSet.next()) {
			User u = new User();
			u.setId(resultSet.getInt("id"));
			u.setUsername(resultSet.getString("username"));
			u.setPassword(resultSet.getString("password"));
			u.setEmail(resultSet.getString("email"));
			userlist.add(u);
		} 
		
		userlist.forEach((item) -> {
			if(username.getText().toString().equals(item.getUsername()) && password.getText().toString().equals(item.getPassword())) {
				try {
					setActiveUser();
					changeToMainMenu(event);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if(resultSet != null) {try {
						resultSet.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}}
					if(statement != null) {try {
						statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}}
					if(connection != null) {try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}}
				}
			}
			else if(username.getText().isEmpty() && password.getText().isEmpty()) {
				errorLabel.setText("Bitte gebe deine Daten ein.");
			}
			else {
				errorLabel.setText("Falsches Passwort oder Benutzername.");
				password.clear();
			}
		});
	}
	@FXML
	private void registerClicked(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
		Stage oldStage = (Stage) source.getScene().getWindow();
		oldStage.close();
		
		//change scene to Register
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setTitle("Registrierung");
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public void setActiveUser() {
		userlist.forEach((item) -> {
			if(item.getUsername().equals(getUsername())) {
				item.isActiveUser();
			}
		});
	}
	
	public void changeToMainMenu(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
		Stage oldStage = (Stage) source.getScene().getWindow();
		oldStage.close();
		
		//change scene to MainMenu
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setTitle("Pokipark | MainMenu");
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public String getUsername() {
		return username.getText().toString();
	}
}