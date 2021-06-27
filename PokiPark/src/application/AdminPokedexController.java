package application;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

import POJO.PokedexPoki;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

public class AdminPokedexController implements Initializable {

	private ObservableList<String> pokedex_OL = FXCollections.observableArrayList();
	private ArrayList<PokedexPoki> pokedex_AL = Database.getPokedex(), currentPokedex_AL = pokedex_AL,
			filteredPokedex_AL = new ArrayList<PokedexPoki>();

	@FXML
	private AnchorPane rootPane;

	@FXML
	private Label pokeInfo_L, pokedexEntry_L;

	@FXML
	private ImageView poke_IV;

	@FXML
	private ListView<String> pokedex_LV;

	@FXML
	private TextField search_TF;

	@FXML
	private void mainmenu_B_Action(ActionEvent event) throws IOException {

		AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminMainMenu.fxml"));
		rootPane.getChildren().setAll(pane);
	}

	@FXML
	private void pokedex_LV_Clicked(MouseEvent event) {

		String clickedPoki = pokedex_LV.getSelectionModel().getSelectedItem();
		boolean pokeFound = false;

		for (int i = 0; i < Database.getPokedex().size() & !pokeFound; i++) {

			if (Database.getPokedex().get(i).getName().equals(clickedPoki)) {

				pokeInfo_L.setText("Name:\n\t" + Database.getPokedex().get(i).getName() + "\nTyp:\n\t"
						+ Database.getPokedex().get(i).getTyp());
				pokedexEntry_L.setText(Database.getPokedex().get(i).getPokedexEntry().toString());
				pokedexEntry_L.setWrapText(true);

				try {
					poke_IV.setImage(new Image(Database.getPokedex().get(i).getName() + ".png"));
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
				pokeFound = true;
			}
		}
	}

	private int idCounter = 0;

	@FXML
	private void pokedexSortId_B_Action(ActionEvent event) throws SQLException {

		PokedexPoki temp;
		int pokedexSize = currentPokedex_AL.size();

		if (idCounter % 2 == 0) {

			for (int i = 0; i < pokedexSize; i++) {

				for (int j = 1; j < pokedexSize - i; j++) {

					if (currentPokedex_AL.get(j - 1).getId() > currentPokedex_AL.get(j).getId()) {

						temp = currentPokedex_AL.get(j - 1);
						currentPokedex_AL.set(j - 1, currentPokedex_AL.get(j));
						currentPokedex_AL.set(j, temp);
					}
				}
			}
		} else if (idCounter % 2 == 1) {

			for (int i = 0; i < pokedexSize; i++) {

				for (int j = 1; j < pokedexSize - i; j++) {

					if (currentPokedex_AL.get(j - 1).getId() < currentPokedex_AL.get(j).getId()) {

						temp = currentPokedex_AL.get(j);
						currentPokedex_AL.set(j, currentPokedex_AL.get(j - 1));
						currentPokedex_AL.set(j - 1, temp);
					}
				}
			}
		}
		idCounter++;
		load_Pokedex_ListView(currentPokedex_AL);
	}

	private int azCounter = 0;

	@FXML
	private void pokedexSortAtoZ_B_Action(ActionEvent event) throws SQLException {

		PokedexPoki temp;
		int pokedexSize = currentPokedex_AL.size();

		if (azCounter % 2 == 1) {

			for (int i = 0; i < pokedexSize; i++) {

				for (int j = 1; j < pokedexSize - i; j++) {

					if (currentPokedex_AL.get(j - 1).getName().charAt(0) < currentPokedex_AL.get(j).getName()
							.charAt(0)) {

						temp = pokedex_AL.get(j - 1);
						currentPokedex_AL.set(j - 1, currentPokedex_AL.get(j));
						currentPokedex_AL.set(j, temp);
					}
				}
			}
		} else if (azCounter % 2 == 0) {

			for (int i = 0; i < pokedexSize; i++) {

				for (int j = 1; j < pokedexSize - i; j++) {

					if (currentPokedex_AL.get(j - 1).getName().charAt(0) > currentPokedex_AL.get(j).getName()
							.charAt(0)) {

						temp = currentPokedex_AL.get(j);
						currentPokedex_AL.set(j, currentPokedex_AL.get(j - 1));
						currentPokedex_AL.set(j - 1, temp);
					}
				}
			}
		}
		azCounter++;
		load_Pokedex_ListView(currentPokedex_AL);
	}

	private int typCounter = 0;

	@FXML
	private void pokedexSortTyp_B_Action(ActionEvent event) throws SQLException {
		
		PokedexPoki temp;
		int pokedexSize = currentPokedex_AL.size();
		
		if (typCounter % 2 == 1) {
			
			for (int i = 0; i < pokedexSize; i++) {
				
				for (int j = 1; j < pokedexSize - i; j++) {
					
					if (currentPokedex_AL.get(j - 1).getTyp().charAt(0) < currentPokedex_AL.get(j).getTyp().charAt(0)) {
						
						temp = currentPokedex_AL.get(j - 1);
						currentPokedex_AL.set(j - 1, currentPokedex_AL.get(j));
						currentPokedex_AL.set(j, temp);
					}
				}
			}
		} else if (typCounter % 2 == 0) {
			
			for (int i = 0; i < pokedexSize; i++) {
				
				for (int j = 1; j < pokedexSize - i; j++) {
					
					if (currentPokedex_AL.get(j - 1).getTyp().charAt(0) > currentPokedex_AL.get(j).getTyp().charAt(0)) {
						
						temp = currentPokedex_AL.get(j);
						currentPokedex_AL.set(j, currentPokedex_AL.get(j - 1));
						currentPokedex_AL.set(j - 1, temp);
					}
				}
			}
		}
		typCounter++;
		load_Pokedex_ListView(currentPokedex_AL);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		try {
			Database.initData("pokedex");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			load_Pokedex_ListView(pokedex_AL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void search_TF_Action(ActionEvent event) throws SQLException {
		
		if (!search_TF.getText().isEmpty()) {
			
			String search = search_TF.getText().toString().toLowerCase();
			filteredPokedex_AL.clear();

			pokedex_AL.forEach(pp -> {
				
				if (pp.getName().toLowerCase().contains(search) | pp.getTyp().toLowerCase().equals(search)
						| String.valueOf(pp.getId()).contains(search)) {
					
					filteredPokedex_AL.add(pp);
				}
			});
			currentPokedex_AL = filteredPokedex_AL;
			load_Pokedex_ListView(filteredPokedex_AL);
			
		} else {
			load_Pokedex_ListView(pokedex_AL);
		}
	}

	public void load_Pokedex_ListView(ArrayList<PokedexPoki> pokedex) throws SQLException {
		
		pokedex_OL.removeAll(pokedex_OL);
		pokedex_LV.getItems().clear();
		;

		pokedex.forEach(pp -> {
			
			pokedex_OL.addAll(pp.getName());
		});

		pokedex_LV.getItems().addAll(pokedex_OL);

		pokedex_LV.setCellFactory(param -> new ListCell<String>() {
			
			private ImageView littlePoke_ImageView = new ImageView();

			@Override
			public void updateItem(String name, boolean empty) {
				
				super.updateItem(name, empty);
				if (empty) {
					
					setText(null);
					setGraphic(null);
					
				} else {
					
					littlePoke_ImageView.setImage(new Image(name + ".png"));
					littlePoke_ImageView.setFitHeight(20);
					littlePoke_ImageView.setFitWidth(20);
					
					setText(name);
					setGraphic(littlePoke_ImageView);
				}
			}
		});
	}
}