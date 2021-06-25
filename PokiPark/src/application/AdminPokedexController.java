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

	private ObservableList<String> pokedex_list = FXCollections.observableArrayList();
	private ArrayList<PokedexPoki> pokedex = Database.getPokedex(), currentPokedex = pokedex,
			filteredPokedex = new ArrayList<PokedexPoki>();

	@FXML
	private AnchorPane rootPane;

	@FXML
	private Label pokeInfo_Label;

	@FXML
	private Label pokedexEntry_Label;

	@FXML
	private ImageView poke_ImageView;

	@FXML
	private ListView<String> pokedex_listview;

	@FXML
	private TextField searchTF;

	@FXML
	private void mainmenuClicked(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminMainMenu.fxml"));
		rootPane.getChildren().setAll(pane);
	}

	@FXML
	private void open_PokeInfo_ListViewClicked(MouseEvent event) {
		String clickedPoki = pokedex_listview.getSelectionModel().getSelectedItem();
		boolean pokeFound = false;
		for (int i = 0; i < Database.getPokedex().size() & !pokeFound; i++) {
			if (Database.getPokedex().get(i).getName().equals(clickedPoki)) {
				pokeInfo_Label.setText("Name:\n\t" + Database.getPokedex().get(i).getName() + "\nTyp:\n\t"
						+ Database.getPokedex().get(i).getTyp());
				pokedexEntry_Label.setText(Database.getPokedex().get(i).getPokedexEntry().toString());
				pokedexEntry_Label.setWrapText(true);
				try {
					poke_ImageView.setImage(new Image(Database.getPokedex().get(i).getName() + ".png"));
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
				pokeFound = true;
			}
		}
	}

	private int idCounter = 0;

	@FXML
	private void sortId_Pokedex_ButtonClicked(ActionEvent event) throws SQLException {
		PokedexPoki temp;
		int pokedexSize = currentPokedex.size();
		if (idCounter % 2 == 0) {
			for (int i = 0; i < pokedexSize; i++) {
				for (int j = 1; j < pokedexSize - i; j++) {
					if (currentPokedex.get(j - 1).getId() > currentPokedex.get(j).getId()) {
						temp = currentPokedex.get(j - 1);
						currentPokedex.set(j - 1, currentPokedex.get(j));
						currentPokedex.set(j, temp);
					}
				}
			}
		} else if (idCounter % 2 == 1) {
			for (int i = 0; i < pokedexSize; i++) {
				for (int j = 1; j < pokedexSize - i; j++) {
					if (currentPokedex.get(j - 1).getId() < currentPokedex.get(j).getId()) {
						temp = currentPokedex.get(j);
						currentPokedex.set(j, currentPokedex.get(j - 1));
						currentPokedex.set(j - 1, temp);
					}
				}
			}
		}
		idCounter++;
		load_Pokedex_ListView(currentPokedex);
	}

	private int azCounter = 0;

	@FXML
	private void sortAtoZ_Pokedex_ButtonClicked(ActionEvent event) throws SQLException {
		PokedexPoki temp;
		int pokedexSize = currentPokedex.size();
		if (azCounter % 2 == 1) {
			for (int i = 0; i < pokedexSize; i++) {
				for (int j = 1; j < pokedexSize - i; j++) {
					if (currentPokedex.get(j - 1).getName().charAt(0) < currentPokedex.get(j).getName().charAt(0)) {
						temp = pokedex.get(j - 1);
						currentPokedex.set(j - 1, currentPokedex.get(j));
						currentPokedex.set(j, temp);
					}
				}
			}
		} else if (azCounter % 2 == 0) {
			for (int i = 0; i < pokedexSize; i++) {
				for (int j = 1; j < pokedexSize - i; j++) {
					if (currentPokedex.get(j - 1).getName().charAt(0) > currentPokedex.get(j).getName().charAt(0)) {
						temp = currentPokedex.get(j);
						currentPokedex.set(j, currentPokedex.get(j - 1));
						currentPokedex.set(j - 1, temp);
					}
				}
			}
		}
		azCounter++;
		load_Pokedex_ListView(currentPokedex);
	}

	private int typCounter = 0;

	@FXML
	private void sortTYP_Pokedex_ButtonClicked(ActionEvent event) throws SQLException {
		PokedexPoki temp;
		int pokedexSize = currentPokedex.size();
		if (typCounter % 2 == 1) {
			for (int i = 0; i < pokedexSize; i++) {
				for (int j = 1; j < pokedexSize - i; j++) {
					if (currentPokedex.get(j - 1).getTyp().charAt(0) < currentPokedex.get(j).getTyp().charAt(0)) {
						temp = currentPokedex.get(j - 1);
						currentPokedex.set(j - 1, currentPokedex.get(j));
						currentPokedex.set(j, temp);
					}
				}
			}
		} else if (typCounter % 2 == 0) {
			for (int i = 0; i < pokedexSize; i++) {
				for (int j = 1; j < pokedexSize - i; j++) {
					if (currentPokedex.get(j - 1).getTyp().charAt(0) > currentPokedex.get(j).getTyp().charAt(0)) {
						temp = currentPokedex.get(j);
						currentPokedex.set(j, currentPokedex.get(j - 1));
						currentPokedex.set(j - 1, temp);
					}
				}
			}
		}
		typCounter++;
		load_Pokedex_ListView(currentPokedex);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			Database.initData("pokedex");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			load_Pokedex_ListView(pokedex);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void searchAction(ActionEvent event) throws SQLException {
		if (!searchTF.getText().isEmpty()) {
			String search = searchTF.getText().toString().toLowerCase();
			filteredPokedex.clear();

			pokedex.forEach(pp -> {
				if (pp.getName().toLowerCase().contains(search) | pp.getTyp().toLowerCase().equals(search)
						| String.valueOf(pp.getId()).contains(search)) {
					filteredPokedex.add(pp);
				}
			});
			currentPokedex = filteredPokedex;
			load_Pokedex_ListView(filteredPokedex);
		} else {
			load_Pokedex_ListView(pokedex);
		}
	}

	public void load_Pokedex_ListView(ArrayList<PokedexPoki> pokedex) throws SQLException {
		pokedex_list.removeAll(pokedex_list);
		pokedex_listview.getItems().clear();

		pokedex.forEach(pp -> {
			pokedex_list.addAll(pp.getName());
		});

		pokedex_listview.getItems().addAll(pokedex_list);

		pokedex_listview.setCellFactory(param -> new ListCell<String>() {
			private ImageView littlePoke_ImageView = new ImageView();

			@Override
			public void updateItem(String name, boolean empty) {
				super.updateItem(name, empty);
				if (empty) {
					setText(null);
					setGraphic(null);
				} else {
					// littlePoke_ImageView.setImage(new Image(name + ".png"));
					littlePoke_ImageView.setImage(new Image("Vulpix.png"));
					littlePoke_ImageView.setFitHeight(14);
					littlePoke_ImageView.setFitWidth(14);
					setText(name);
					setGraphic(littlePoke_ImageView);
				}
			}
		});
	}
}