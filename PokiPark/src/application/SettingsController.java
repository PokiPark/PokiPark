package application;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

import POJO.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

public class SettingsController extends RegisterController implements Initializable {

	@FXML
	private AnchorPane rootPane;

	@FXML
	private Pane contentPane;

	@FXML
	private ListView<String> settings_LV, settingsSpecified_LV;

	@FXML
	private Label contentPane_L;

	@FXML
	private TextField contentPane_TF1, contentPane_TF2;

	@FXML
	private Button contentPane_B3;

	private ObservableList<String> settings_OL = FXCollections.observableArrayList("Account"),
			settingsSpecified_OL = FXCollections.observableArrayList();

	private int lv1_Counter, lv2_Counter;

	private User u;

	// FXML-METHODS //

	@FXML
	private void item_LV_Clicked(MouseEvent event) throws IOException, SQLException {

		contentPane_L.setText(null);
		showAccountSettings();
	}

	@FXML
	private void itemSpecified_LV_Clicked(MouseEvent event) {

		switch (lv1_Counter) {

		case 0:
			contentPane_ShowAccountSettings();
			break;
		}
	}

	@FXML
	private void contentPane_B3_Action(ActionEvent event) throws SQLException {

		switch (lv2_Counter) {

		case 0:
			database_UpdateUsername();
			break;

		case 1:
			database_UpdateEmail();
			break;

		case 2:
			database_UpdatePassword();
			break;
		}
	}
	
	@FXML
	private void mainmenu_buttonAction(ActionEvent event) throws IOException {

		AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		rootPane.getChildren().setAll(pane);
	}

	// END OF FXML-METHODS //

	private void showAccountSettings() {
		lv1_Counter = 0;

		initialize(null, null);

		settingsSpecified_LV.setPrefHeight(340);
		settingsSpecified_OL.clear();
		settingsSpecified_LV.getItems().clear();
		settingsSpecified_OL.addAll("Benutzername", "Email", "Passwort");
		settingsSpecified_LV.getItems().addAll(settingsSpecified_OL);
	}

	private void contentPane_ShowAccountSettings() {

		contentPane_B3.setVisible(true);
		contentPane_TF1.setVisible(false);
		contentPane_TF2.setVisible(true);

		if (settingsSpecified_LV.getSelectionModel().getSelectedItem().equals("Benutzername")) {
			
			contentPane_ShowUsernameSettings();
			lv2_Counter = 0;

		} else if (settingsSpecified_LV.getSelectionModel().getSelectedItem().equals("Email")) {
			
			contentPane_ShowEmailSettings();
			lv2_Counter = 1;

		} else if (settingsSpecified_LV.getSelectionModel().getSelectedItem().equals("Passwort")) {
			
			contentPane_ShowPasswordSettings();
			lv2_Counter = 2;
		}
	}

	private void contentPane_ShowUsernameSettings() {

		contentPane_L.setText("Benutzername:\n\t" + u.getUsername() + "\n\nändere deinen Benutzernamen:\n");
		contentPane_TF2.setPromptText("neuer Benutzername");
		contentPane_B3.setText("Bestätigen");
	}

	private void contentPane_ShowEmailSettings() {

		contentPane_L.setText("Email:\n\t" + u.getEmail() + "\n\nändere deine Email:\n");
		contentPane_TF2.setPromptText("neue Email");
		contentPane_B3.setText("Bestätigen");
	}

	private void contentPane_ShowPasswordSettings() {

		contentPane_TF1.setVisible(true);
		contentPane_L.setText("Ändere dein Passwort:");
		contentPane_TF1.setPromptText("altes Passwort");
		contentPane_TF2.setPromptText("neues Passwort");
		contentPane_B3.setText("Bestätigen");
	}

	// BEGIN OF DATABASE-METHODS //

	private void database_UpdateUsername() throws SQLException {

		if (usernameIsValid(Database.getUserlist(), contentPane_TF2.getText().toString())) {
			
			Database.sendSqlCommand("UPDATE usertable SET username = '" + contentPane_TF2.getText().toString()
					+ "'  WHERE id = " + u.getId() + ";");
			Database.initData("usertable");
			Database.activeUser.setUsername(contentPane_TF2.getText().toString());
			
			initialize(null, null);
			contentPane_ShowUsernameSettings();
			
		} else {
			System.err.println("Benutzername ist bereits in Verwendung.");
		}
	}

	private void database_UpdateEmail() throws SQLException {
		
		if (emailIsValid(Database.getUserlist(), contentPane_TF2.getText().toString())
				&& emailHasCorrectSemantics(contentPane_TF2.getText().toString())) {
			
			Database.sendSqlCommand("UPDATE usertable SET email = '" + contentPane_TF2.getText().toString()
					+ "'  WHERE id = " + u.getId() + ";");
			Database.initData("usertable");
			Database.activeUser.setEmail(contentPane_TF2.getText().toString());
			
			initialize(null, null);
			contentPane_ShowEmailSettings();
			
		} else {
			System.err.println("Email ist nicht gültig oder bereits in Verwendung.");
		}
	}

	private void database_UpdatePassword() throws SQLException {

		if (contentPane_TF1.getText().toString().equals(u.getPassword())
				&& passwordIsValid(contentPane_TF2.getText().toString())) {
			
			Database.sendSqlCommand("UPDATE usertable SET password = '" + contentPane_TF2.getText().toString()
					+ "'  WHERE id = " + u.getId() + ";");
			Database.initData("usertable");
			initialize(null, null);
			contentPane_ShowPasswordSettings();
			
		} else {
			System.err.println(
					"Altes Passwort ist nicht korrekt oder\nneues Passwort entspricht nicht den Anforderungen.");
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		try {
			Database.initData("usertable");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		u = Database.activeUser;

		settings_LV.getItems().clear();
		settings_LV.getItems().addAll(settings_OL);

		contentPane_B3.setVisible(false);
		contentPane_TF1.setVisible(false);
		contentPane_TF2.setVisible(false);

		contentPane_L.setText(null);

		// END OF DATABASE-METHODS //
	}
}