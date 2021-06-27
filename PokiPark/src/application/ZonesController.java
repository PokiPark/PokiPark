package application;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

public class ZonesController implements Initializable {

	@FXML
	private AnchorPane rootPane, contentPane;

	@FXML
	private ListView<String> zones_LV;

	@FXML
	private Button water_B, weed_B, desert_B, mountain_B, map_B;

	@FXML
	private ImageView main_IV, water_IV, weed_IV, desert_IV, mountain_IV;

	private ObservableList<String> ol = FXCollections.observableArrayList("See", "Wiese / Wald", "Wüste", "Gebirge");

	private int pokiCount = 0;

	private ArrayList<Button> poki_BL = new ArrayList<Button>();;

	// START OF FXML-METHODS //

	@FXML
	private void zones_LV_Clicked(MouseEvent event) {

		String select = zones_LV.getSelectionModel().getSelectedItem();

		if (select.equals("See")) {

			main_IV.setImage(new Image("Wasser.png"));

			disableZoneButtons();
			map_B.setVisible(true);

			loadPokis();
		} else if (select.equals("Wiese / Wald")) {

			main_IV.setImage(new Image("Weed.png"));

			disableZoneButtons();
			map_B.setVisible(true);

			loadPokis();
		} else if (select.equals("Wüste")) {

			main_IV.setImage(new Image("Wüste.png"));

			disableZoneButtons();
			map_B.setVisible(true);

			loadPokis();
		} else if (select.equals("Gebirge")) {

			main_IV.setImage(new Image("Stein mit Schnee.png.png"));

			disableZoneButtons();
			map_B.setVisible(true);

			loadPokis();
		}
	}

	@FXML
	private void water_B_Action(ActionEvent event) {

		main_IV.setImage(new Image("Wasser.png"));

		disableZoneButtons();
		map_B.setVisible(true);

		loadPokis();
	}

	@FXML
	private void weed_B_Action(ActionEvent event) {

		main_IV.setImage(new Image("Weed.png"));

		disableZoneButtons();
		map_B.setVisible(true);

		loadPokis();
	}

	@FXML
	private void desert_B_Action(ActionEvent event) {

		main_IV.setImage(new Image("Wüste.png"));

		disableZoneButtons();
		map_B.setVisible(true);

		loadPokis();
	}

	@FXML
	private void mountain_B_Action(ActionEvent event) {

		main_IV.setImage(new Image("Stein mit Schnee.png"));

		disableZoneButtons();
		map_B.setVisible(true);

		loadPokis();
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

		AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
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

	private void loadPokis() {

		for (int i = 0; i < pokiCount; i++) {
			contentPane.getChildren().remove(poki_BL.get(i));
		}
		pokiCount = 0;
		poki_BL.clear();

		Database.getPokilist().forEach(p -> {
			pokiCount = pokiCount + p.getAnzahl();
		});

		for (int i = 0; i < pokiCount; i++) {
			poki_BL.add(new Button());
			poki_BL.get(i).setPrefSize(50, 50);
			poki_BL.get(i).setLayoutX(5 + Math.random() * 440);
			poki_BL.get(i).setLayoutY(5 + Math.random() * 365);
			poki_BL.get(i).setVisible(true);
			contentPane.getChildren().add(poki_BL.get(i));
		}

		int temp = 0;
		int a = 0;

		for (int i = 0; i < Database.getPokilist().size(); i++) {

			for (int j = 0; j < Database.getPokilist().get(i).getAnzahl(); j++) {

				if (j >= temp) {
					temp = j;
					ImageView poke_IV = new ImageView(new Image(Database.getPokilist().get(i).getName() + "M.png"));
					poke_IV.setFitHeight(50);
					poke_IV.setFitWidth(50);
					poki_BL.get(j + a).setGraphic(poke_IV);
				} else {
					a = temp + 1;
					temp = j;
					ImageView poke_IV = new ImageView(new Image(Database.getPokilist().get(i).getName() + "M.png"));
					poke_IV.setFitHeight(50);
					poke_IV.setFitWidth(50);
					poki_BL.get(j + a).setGraphic(poke_IV);
				}

			}
		}

	}

	private void hidePokis() {

		for (int i = 0; i < pokiCount; i++) {
			contentPane.getChildren().remove(poki_BL.get(i));
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