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
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

public class AdminZonesController implements Initializable {

	@FXML
	private AnchorPane rootPane, contentPane;

	@FXML
	private ListView<String> zones_LV;

	@FXML
	private Button water_B, weed_B, desert_B, mountain_B, map_B;

	@FXML
	private ImageView main_IV, water_IV, weed_IV, desert_IV, mountain_IV;

	private ArrayList<Poki> pokilist = new ArrayList<Poki>();

	private ObservableList<String> ol = FXCollections.observableArrayList("See", "Wiese / Wald", "Wüste", "Gebirge");

	private int pokiCount = 0;

	private ArrayList<ImageView> poki_IVL = new ArrayList<ImageView>();;

	// START OF FXML-METHODS //

	@FXML
	private void zones_LV_Clicked(MouseEvent event) {

		String select = zones_LV.getSelectionModel().getSelectedItem();

		if (select.equals("See")) {

			main_IV.setImage(new Image("Wasser.png"));
			main_IV.setLayoutX(0);
			main_IV.setLayoutY(0);

			disableZoneButtons();
			map_B.setVisible(true);

			loadPokis(0);
		} else if (select.equals("Wiese / Wald")) {

			main_IV.setImage(new Image("Weed.png"));
			main_IV.setLayoutX(0);
			main_IV.setLayoutY(0);

			disableZoneButtons();
			map_B.setVisible(true);

			loadPokis(1);
		} else if (select.equals("Wüste")) {

			main_IV.setImage(new Image("Wüste.png"));
			main_IV.setLayoutX(0);
			main_IV.setLayoutY(0);

			disableZoneButtons();
			map_B.setVisible(true);

			loadPokis(2);
		} else if (select.equals("Gebirge")) {

			main_IV.setImage(new Image("Stein mit Schnee.png"));
			main_IV.setLayoutX(0);
			main_IV.setLayoutY(0);

			disableZoneButtons();
			map_B.setVisible(true);

			loadPokis(3);
		}
	}

	@FXML
	private void water_B_Action(ActionEvent event) {

		main_IV.setImage(new Image("Wasser.png"));

		disableZoneButtons();
		map_B.setVisible(true);

		loadPokis(0);
	}

	@FXML
	private void weed_B_Action(ActionEvent event) {

		main_IV.setImage(new Image("Weed.png"));

		disableZoneButtons();
		map_B.setVisible(true);

		loadPokis(1);
	}

	@FXML
	private void desert_B_Action(ActionEvent event) {

		main_IV.setImage(new Image("Wüste.png"));

		disableZoneButtons();
		map_B.setVisible(true);

		loadPokis(2);
	}

	@FXML
	private void mountain_B_Action(ActionEvent event) {

		main_IV.setImage(new Image("Stein mit Schnee.png"));

		disableZoneButtons();
		map_B.setVisible(true);

		loadPokis(3);
	}

	@FXML
	private void map_B_Action(ActionEvent event) {

		main_IV.setImage(new Image("PokiMap.png"));

		enableZoneButtons();
		map_B.setVisible(false);

		hidePokis();
	}

	@FXML
	public void mainmenu_B_Action(ActionEvent event) throws IOException {

		AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminMainMenu.fxml"));
		rootPane.getChildren().setAll(pane);
	}

	// END OF FXML-METHODS //

	private void enableZoneButtons() {

		water_B.setVisible(true);
		weed_B.setVisible(true);
		desert_B.setVisible(true);
		mountain_B.setVisible(true);
	}

	private void disableZoneButtons() {

		water_B.setVisible(false);
		weed_B.setVisible(false);
		desert_B.setVisible(false);
		mountain_B.setVisible(false);
	}

	private void loadPokis(int zone) {

		for (int i = 0; i < pokilist.size(); i++) {
			contentPane.getChildren().remove(poki_IVL.get(i));
		}
		pokilist.clear();
		poki_IVL.clear();

		switch (zone) {

		case 0:
			Database.getPokilist().forEach(p -> {
				pokiCount = 0;
				for (int i = 0; i < pokilist.size(); i++) {
					if (pokilist.get(i).getName().equals(p.getName())) {
						pokiCount++;
					}
				}
				if ((p.getTyp().contains("Wasser") | p.getTyp().contains("Flug")) & pokiCount <= p.getAnzahl()) {
					for (int i = 0; i < p.getAnzahl(); i++)
						pokilist.add(p);
				}
			});
			break;

		case 1:
			Database.getPokilist().forEach(p -> {
				pokiCount = 0;
				for (int i = 0; i < pokilist.size(); i++) {
					if (pokilist.get(i).getName().equals(p.getName())) {
						pokiCount++;
					}
				}
				if ((p.getTyp().contains("Pflanze") | p.getTyp().contains("Normal") | p.getTyp().contains("Käfer")
						| p.getTyp().contains("Gift") | p.getTyp().contains("Psycho") | p.getTyp().contains("Geist")
						| p.getTyp().contains("Unlicht") | p.getTyp().contains("Fee")) & pokiCount <= p.getAnzahl()) {
					for (int i = 0; i < p.getAnzahl(); i++)
						pokilist.add(p);
				}
			});
			break;

		case 2:
			Database.getPokilist().forEach(p -> {
				pokiCount = 0;
				for (int i = 0; i < pokilist.size(); i++) {
					if (pokilist.get(i).getName().equals(p.getName())) {
						pokiCount++;
					}
				}
				if ((p.getTyp().contains("Normal") | p.getTyp().contains("Feuer") | p.getTyp().contains("Gestein")
						| p.getTyp().contains("Boden") | p.getTyp().contains("Kampf") | p.getTyp().contains("Stahl"))
						& pokiCount <= p.getAnzahl()) {
					for (int i = 0; i < p.getAnzahl(); i++)
						pokilist.add(p);
				}
			});
			break;

		case 3:
			Database.getPokilist().forEach(p -> {
				pokiCount = 0;
				for (int i = 0; i < pokilist.size(); i++) {
					if (pokilist.get(i).getName().equals(p.getName())) {
						pokiCount++;
					}
				}
				if ((p.getTyp().contains("Feuer") | p.getTyp().contains("Elektro") | p.getTyp().contains("Flug")
						| p.getTyp().contains("Gestein") | p.getTyp().contains("Boden") | p.getTyp().contains("Kampf")
						| p.getTyp().contains("Eis") | p.getTyp().contains("Psycho") | p.getTyp().contains("Drache")
						| p.getTyp().contains("Stahl") | p.getTyp().contains("Fee")) & pokiCount <= p.getAnzahl()) {
					for (int i = 0; i < p.getAnzahl(); i++)
						pokilist.add(p);
				}
			});
			break;
		}

		for (int i = 0; i < pokilist.size(); i++) {
			poki_IVL.add(new ImageView());
			poki_IVL.get(i).setFitHeight(50);
			poki_IVL.get(i).setFitWidth(50);
			
			if(Math.random() > 0.5) poki_IVL.get(i).setScaleX(-1);

			poki_IVL.get(i).setLayoutX(30 + Math.random() * 370);
			poki_IVL.get(i).setLayoutY(30 + Math.random() * 270);

			poki_IVL.get(i).setImage(new Image(pokilist.get(i).getName() + "M.png"));

			poki_IVL.get(i).setVisible(true);
			contentPane.getChildren().add(poki_IVL.get(i));
		}
	}

	private void hidePokis() {

		for (int i = 0; i < pokilist.size(); i++) {
			contentPane.getChildren().remove(poki_IVL.get(i));
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		try {
			Database.initData("pokitable");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		zones_LV.getItems().clear();
		zones_LV.getItems().setAll(ol);
	}
}