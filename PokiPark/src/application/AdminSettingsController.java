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
import javafx.scene.text.Font;

public class AdminSettingsController extends RegisterController implements Initializable {

	@FXML
	private AnchorPane rootPane;

	@FXML
	private Pane contentPane;

	@FXML
	private ListView<String> settings_LV, settingsSpecified_LV;

	@FXML
	private Label contentPane_L;

	@FXML
	private TextField settingsSpecified_TF, contentPane_TF1, contentPane_TF2, contentPane_TF3, contentPane_TF4,
			contentPane_TF5, contentPane_TF6;

	@FXML
	private Button settingsSpecified_B, contentPane_B1, contentPane_B2, contentPane_B3, contentPane_B4, contentPane_B5,
			contentPane_B6, contentPane_B7;

	private ObservableList<String> settings_OL = FXCollections.observableArrayList("Account",
			"Übersicht Benutzerkonten", "Übersicht Pokedex", "Übersicht Park-Pokis"),
			settingsSpecified_OL = FXCollections.observableArrayList();

	private int lv1_Counter, lv2_Counter;

	boolean isUserAdmin;

	private User u, userTarget;
	private PokedexPoki pokedexPokiTarget;
	private Poki pokiTarget;

	// FXML-METHODS //

	@FXML
	private void item_LV_Clicked(MouseEvent event) throws IOException, SQLException {

		String selection = settings_LV.getSelectionModel().getSelectedItem();

		if (selection.equals("Account")) {
			showAccountSettings();
			lv1_Counter = 0;

		} else if (selection.equals("Übersicht Benutzerkonten")) {
			showUserList();
			lv1_Counter = 1;

		} else if (selection.equals("Übersicht Pokedex")) {
			showPokedex();
			lv1_Counter = 2;

		} else if (selection.equals("Übersicht Park-Pokis")) {
			showPokiList();
			lv1_Counter = 3;
		}
	}

	@FXML
	private void itemSpecified_LV_Clicked(MouseEvent event) {

		switch (lv1_Counter) {

		case 0:
			contentPane_ShowAccountSettings();
			break;

		case 1:
			contentPane_ShowUserTargetSettings();
			break;

		case 2:
			contentPane_ShowPokedexPokiTargetSettings();
			break;

		case 3:
			contentPane_ShowPokiTargetSettings();
			break;
		}
	}

	@FXML
	private void settingsSpecified_TF_Action(ActionEvent event) {
		settingsSpecified_B_Action(event);
	}

	@FXML
	private void settingsSpecified_B_Action(ActionEvent event) {

		switch (lv1_Counter) {

		case 1:
			contentPane_AddEntryToUserList();
			break;

		case 2:
			contentPane_AddEntryToPokedex();
			break;

		case 3:
			contentPane_AddEntryToPokiList();
			break;
		}
	}

	@FXML
	private void contentPane_B1_Action(ActionEvent event) {

		switch (lv2_Counter) {

		case 5:
			contentPane_ShowPokedexPokiTargetSettings();
			break;
		}
	}

	@FXML
	private void contentPane_B2_Action(ActionEvent event) {

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

		case 3:
			database_DeleteUserTarget();
			break;

		case 7:
			database_InsertNewUser();
			break;
		}
		initialize(null, null);
	}

	@FXML
	private void contentPane_B4_Action(ActionEvent event) throws SQLException {

		switch (lv2_Counter) {

		case 3:
			database_UpdateUserTargetToAdmin();
			break;

		case 6:
			database_UpdatePokiTargetAnzahl();
			break;
		}
		initialize(null, null);
	}

	@FXML
	private void contentPane_B5_Action(ActionEvent event) {

		switch (lv2_Counter) {

		case 4:
			contentPane_ShowPokedexEntry();
			break;

		case 7:
			contentPane_SetNewUserToAdmin();
			break;
		}
	}

	@FXML
	private void contentPane_B6_Action(ActionEvent event) {

		switch (lv2_Counter) {

		case 6:
			contentPane_SubtractFromPokiCount();
			break;
		}
	}

	@FXML
	private void contentPane_B7_Action(ActionEvent event) {

		switch (lv2_Counter) {

		case 6:
			contentPane_AddToPokiCount();
			break;
		}
	}

	@FXML
	private void mainmenu_buttonAction(ActionEvent event) throws IOException {

		AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminMainMenu.fxml"));
		rootPane.getChildren().setAll(pane);
	}

	// END OF FXML-METHODS //

	private void showAccountSettings() {
		lv1_Counter = 0;

		settingsSpecified_TF.setVisible(false);
		settingsSpecified_B.setVisible(false);
		settingsSpecified_LV.setPrefHeight(340);
		settingsSpecified_OL.clear();
		settingsSpecified_LV.getItems().clear();
		settingsSpecified_OL.addAll("Benutzername", "Email", "Passwort");
		settingsSpecified_LV.getItems().addAll(settingsSpecified_OL);
	}

	private void showUserList() throws SQLException {

		settingsSpecified_TF.setVisible(true);
		settingsSpecified_B.setVisible(true);
		settingsSpecified_LV.setPrefHeight(300);
		settingsSpecified_OL.clear();
		settingsSpecified_LV.getItems().clear();
		Database.initData("usertable");
		Database.getUserlist().forEach(u -> {
			settingsSpecified_OL.add(u.getUsername());
		});

		settingsSpecified_LV.getItems().addAll(settingsSpecified_OL);
	}

	private void showPokedex() throws SQLException {

		settingsSpecified_TF.setVisible(true);
		settingsSpecified_B.setVisible(true);
		settingsSpecified_LV.setPrefHeight(300);
		settingsSpecified_OL.clear();
		settingsSpecified_LV.getItems().clear();
		Database.initData("pokedex");
		Database.getPokedex().forEach(pp -> {
			settingsSpecified_OL.add(pp.getName());
		});

		settingsSpecified_LV.getItems().addAll(settingsSpecified_OL);
	}

	private void showPokiList() throws SQLException {

		settingsSpecified_TF.setVisible(true);
		settingsSpecified_B.setVisible(true);
		settingsSpecified_LV.setPrefHeight(300);
		settingsSpecified_OL.clear();
		settingsSpecified_LV.getItems().clear();
		Database.initData("pokitable");
		Database.getPokilist().forEach(p -> {
			settingsSpecified_OL.add(p.getName());
		});

		settingsSpecified_LV.getItems().addAll(settingsSpecified_OL);
	}

	private void contentPane_ShowAccountSettings() {

		contentPane_B1.setVisible(false);
		contentPane_B2.setVisible(false);
		contentPane_B3.setVisible(true);
		contentPane_B4.setVisible(false);
		contentPane_B5.setVisible(false);
		contentPane_B6.setVisible(false);
		contentPane_B7.setVisible(false);
		contentPane_TF1.setVisible(false);
		contentPane_TF2.setVisible(true);
		contentPane_TF3.setVisible(false);
		contentPane_TF4.setVisible(false);
		contentPane_TF5.setVisible(false);
		contentPane_TF6.setVisible(false);

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

	private void contentPane_ShowUserTargetSettings() {
		lv2_Counter = 3;

		contentPane_B1.setVisible(false);
		contentPane_B2.setVisible(false);
		contentPane_B3.setVisible(true);
		contentPane_B4.setVisible(false);
		contentPane_B5.setVisible(false);
		contentPane_B6.setVisible(false);
		contentPane_B7.setVisible(false);
		contentPane_TF1.setVisible(false);
		contentPane_TF2.setVisible(false);
		contentPane_TF3.setVisible(false);
		contentPane_TF4.setVisible(false);
		contentPane_TF5.setVisible(false);
		contentPane_TF6.setVisible(false);

		contentPane_B3.setText("Account löschen");

		boolean foundUser = false;
		for (int i = 0; i < Database.getUserlist().size() & !foundUser; i++) {
			if (Database.getUserlist().get(i).getUsername()
					.equals(settingsSpecified_LV.getSelectionModel().getSelectedItem())) {

				userTarget = Database.getUserlist().get(i);
				contentPane_L
						.setText("Benutzername:\n\t" + userTarget.getUsername() + "\nEmail:\n\t" + userTarget.getEmail()
								+ "\nID:\n\t" + userTarget.getId() + "\nist Admin:\n\t" + userTarget.isAdmin());

				if (!userTarget.isAdmin()) {
					contentPane_B4.setVisible(true);
					contentPane_B4.setText("ernenne zum Admin");
				}

				foundUser = true;
			}
		}
	}

	private void contentPane_ShowPokedexPokiTargetSettings() {
		lv2_Counter = 4;

		contentPane_B1.setVisible(false);
		contentPane_B2.setVisible(false);
		contentPane_B3.setVisible(true);
		contentPane_B4.setVisible(false);
		contentPane_B5.setVisible(true);
		contentPane_B6.setVisible(false);
		contentPane_B7.setVisible(false);
		contentPane_TF1.setVisible(false);
		contentPane_TF2.setVisible(false);
		contentPane_TF3.setVisible(false);
		contentPane_TF4.setVisible(true);
		contentPane_TF5.setVisible(false);
		contentPane_TF6.setVisible(false);

		contentPane_B3.setText("Eintrag löschen");
		contentPane_B5.setText("Pokedexeintrag");

		boolean foundPokedexPoki = false;
		for (int i = 0; i < Database.getPokedex().size() & !foundPokedexPoki; i++) {
			if (Database.getPokedex().get(i).getName()
					.equals(settingsSpecified_LV.getSelectionModel().getSelectedItem())) {

				pokedexPokiTarget = Database.getPokedex().get(i);
				contentPane_ShowPokedexInfo();

				foundPokedexPoki = true;
			}
		}
	}

	private void contentPane_ShowPokedexInfo() {

		if (!pokedexPokiTarget.getSecondEvo().equals("NULL") && pokedexPokiTarget.getThirdEvo().equals("NULL")) {
			contentPane_L.setText("Name:\n\t" + pokedexPokiTarget.getName() + "\nTyp:\n\t" + pokedexPokiTarget.getTyp()
					+ "\nEntwicklung:\n\t" + pokedexPokiTarget.getFirstEvo() + "\n\t" + pokedexPokiTarget.getSecondEvo()
					+ "\n\nID:\t" + pokedexPokiTarget.getId() + "\n\nZeige Pokedexeintrag:");

		} else if (pokedexPokiTarget.getSecondEvo().equals("NULL") && pokedexPokiTarget.getThirdEvo().equals("NULL")) {
			contentPane_L.setText("Name:\n\t" + pokedexPokiTarget.getName() + "\nTyp:\n\t" + pokedexPokiTarget.getTyp()
					+ "\nEntwicklung:\n\t" + pokedexPokiTarget.getFirstEvo() + "\n\n\nID:\t" + pokedexPokiTarget.getId()
					+ "\n\nZeige Pokedexeintrag:");

		} else {
			contentPane_L.setText("Name:\n\t" + pokedexPokiTarget.getName() + "\nTyp:\n\t" + pokedexPokiTarget.getTyp()
					+ "\nEntwicklung:\n\t" + pokedexPokiTarget.getFirstEvo() + "\n\t" + pokedexPokiTarget.getSecondEvo()
					+ "\n\t" + pokedexPokiTarget.getThirdEvo() + "\nID:\t" + pokedexPokiTarget.getId()
					+ "\n\nZeige Pokedexeintrag:");
		}
	}

	private void contentPane_ShowPokedexEntry() {
		lv2_Counter = 5;

		contentPane_B1.setVisible(true);
		contentPane_B2.setVisible(false);
		contentPane_B3.setVisible(false);
		contentPane_B4.setVisible(false);
		contentPane_B5.setVisible(false);
		contentPane_B6.setVisible(false);
		contentPane_B7.setVisible(false);
		contentPane_TF1.setVisible(false);
		contentPane_TF2.setVisible(false);
		contentPane_TF3.setVisible(false);
		contentPane_TF4.setVisible(false);
		contentPane_TF5.setVisible(false);
		contentPane_TF6.setVisible(false);

		contentPane_B1.setText("Zurück");
		contentPane_L.setText(pokedexPokiTarget.getName() + ":\n\n" + pokedexPokiTarget.getPokedexEntry());
	}

	private void contentPane_ShowPokiTargetSettings() {
		lv2_Counter = 6;

		contentPane_B1.setVisible(false);
		contentPane_B2.setVisible(false);
		contentPane_B3.setVisible(false);
		contentPane_B4.setVisible(true);
		contentPane_B5.setVisible(false);
		contentPane_B6.setVisible(true);
		contentPane_B7.setVisible(true);
		contentPane_TF1.setVisible(false);
		contentPane_TF2.setVisible(false);
		contentPane_TF3.setVisible(false);
		contentPane_TF4.setVisible(false);
		contentPane_TF5.setVisible(false);
		contentPane_TF6.setVisible(true);

		contentPane_B4.setText("Bestätigen");
		contentPane_B6.setText("-");
		contentPane_B7.setText("+");

		boolean foundPoki = false;
		for (int i = 0; i < Database.getPokilist().size() & !foundPoki; i++) {
			if (Database.getPokilist().get(i).getName()
					.equals(settingsSpecified_LV.getSelectionModel().getSelectedItem())) {

				pokiTarget = Database.getPokilist().get(i);
				contentPane_L.setText("Name:\n\t" + pokiTarget.getName() + "\nTyp:\n\t" + pokiTarget.getTyp()
						+ "\n\nAnzahl:\t" + pokiTarget.getAnzahl() + "\n\n");
				contentPane_TF6.setText(String.valueOf(pokiTarget.getAnzahl()));

				foundPoki = true;
			}
		}
	}

	private void contentPane_SubtractFromPokiCount() {

		int count = Integer.parseInt(contentPane_TF6.getText());
		count--;
		contentPane_TF6.setText(String.valueOf(count));

		if (count <= 0)
			contentPane_B6.setVisible(false);
	}

	private void contentPane_AddToPokiCount() {

		int count = Integer.parseInt(contentPane_TF6.getText());
		count++;
		contentPane_TF6.setText(String.valueOf(count));

		if (count > 0)
			contentPane_B6.setVisible(true);
	}

	private void contentPane_AddEntryToUserList() {
		lv2_Counter = 7;

		contentPane_B1.setVisible(false);
		contentPane_B2.setVisible(false);
		contentPane_B3.setVisible(true);
		contentPane_B4.setVisible(false);
		contentPane_B5.setVisible(true);
		contentPane_B6.setVisible(false);
		contentPane_B7.setVisible(false);
		contentPane_TF1.setVisible(true);
		contentPane_TF2.setVisible(true);
		contentPane_TF3.setVisible(true);
		contentPane_TF4.setVisible(false);
		contentPane_TF5.setVisible(false);
		contentPane_TF6.setVisible(false);

		contentPane_L.setFont(new Font("Artifakt Element", 15));
		contentPane_L.setText("\tNeuer Nutzer:");
		contentPane_TF1.setPromptText("Benutzername");
		contentPane_TF1.setText(settingsSpecified_TF.getText().toString());
		contentPane_TF2.setPromptText("Email");
		contentPane_TF3.setPromptText("Passwort");
		settingsSpecified_TF.clear();
		contentPane_B3.setText("Bestätigen");
		contentPane_B5.setText("Adminrechte hinzufügen");
	}

	private void contentPane_SetNewUserToAdmin() {

		if (isUserAdmin) {
			contentPane_B5.setText("Adminrecht hinzufügen");
			isUserAdmin = false;
		} else {
			contentPane_B5.setText("Adminrecht entfernen");
			isUserAdmin = true;
		}
	}

	private void contentPane_AddEntryToPokedex() {
		lv2_Counter = 8;

		contentPane_B1.setVisible(false);
		contentPane_B2.setVisible(false);
		contentPane_B3.setVisible(false);
		contentPane_B4.setVisible(false);
		contentPane_B5.setVisible(false);
		contentPane_B6.setVisible(false);
		contentPane_B7.setVisible(false);
		contentPane_TF1.setVisible(false);
		contentPane_TF2.setVisible(false);
		contentPane_TF3.setVisible(false);
		contentPane_TF4.setVisible(false);
		contentPane_TF5.setVisible(false);
		contentPane_TF6.setVisible(false);
	}

	private void contentPane_AddEntryToPokiList() {
		lv2_Counter = 9;

		contentPane_B1.setVisible(false);
		contentPane_B2.setVisible(false);
		contentPane_B3.setVisible(false);
		contentPane_B4.setVisible(false);
		contentPane_B5.setVisible(false);
		contentPane_B6.setVisible(false);
		contentPane_B7.setVisible(false);
		contentPane_TF1.setVisible(false);
		contentPane_TF2.setVisible(false);
		contentPane_TF3.setVisible(false);
		contentPane_TF4.setVisible(false);
		contentPane_TF5.setVisible(false);
		contentPane_TF6.setVisible(false);
	}

	private void database_UpdateUsername() throws SQLException {

		if (usernameIsValid(Database.getUserlist(), contentPane_TF2.getText().toString())) {
			Database.sendSqlCommand("UPDATE usertable SET username = '" + contentPane_TF2.getText().toString()
					+ "'  WHERE id = " + u.getId() + ";");
			Database.initData("usertable");
			initialize(null, null);
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
			initialize(null, null);
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
		} else {
			System.err.println(
					"Altes Passwort ist nicht korrekt oder\nneues Passwort entspricht nicht den Anforderungen.");
		}
	}

	private void database_DeleteUserTarget() throws SQLException {

		Database.sendSqlCommand("DELETE FROM usertable WHERE id = " + userTarget.getId() + ";");
		initialize(null, null);
	}

	private void database_UpdateUserTargetToAdmin() throws SQLException {

		Database.sendSqlCommand("UPDATE usertable SET admin = 1 WHERE id = " + userTarget.getId() + ";");
		initialize(null, null);
	}

	private void database_UpdatePokiTargetAnzahl() throws NumberFormatException, SQLException {

		Database.sendSqlCommand("UPDATE pokitable SET anzahl = " + Integer.parseInt(contentPane_TF6.getText())
				+ " WHERE id = " + pokiTarget.getId() + ";");
		initialize(null, null);
	}

	private void database_InsertNewUser() throws SQLException {

		String username = contentPane_TF1.getText().toString();
		String email = contentPane_TF2.getText().toString();
		String password = contentPane_TF3.getText().toString();
		int admin;

		if (isUserAdmin)
			admin = 1;
		else
			admin = 0;

		if (usernameIsValid(Database.getUserlist(), username) && emailIsValid(Database.getUserlist(), email)
				&& passwordIsValid(password))
			Database.sendSqlCommand("INSERT INTO usertable (username, password, email, id, admin) VALUES ('" + username
					+ "', '" + password + "', '" + email + "', NULL, " + admin + ");");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		try {
			Database.initData("usertable");
			Database.initData("pokedex");
			Database.initData("pokitable");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		u = Database.activeUser;

		settings_LV.getItems().clear();
		settings_LV.getItems().addAll(settings_OL);

		contentPane_L.setWrapText(true);

		settingsSpecified_TF.setVisible(false);
		settingsSpecified_B.setVisible(false);

		contentPane_B1.setVisible(false);
		contentPane_B2.setVisible(false);
		contentPane_B3.setVisible(false);
		contentPane_B4.setVisible(false);
		contentPane_B5.setVisible(false);
		contentPane_B6.setVisible(false);
		contentPane_B7.setVisible(false);
		contentPane_TF1.setVisible(false);
		contentPane_TF2.setVisible(false);
		contentPane_TF3.setVisible(false);
		contentPane_TF4.setVisible(false);
		contentPane_TF5.setVisible(false);
		contentPane_TF6.setVisible(false);

		contentPane_L.setText(null);
		contentPane_L.setFont(new Font("Artifakt Element", 12));
	}
}