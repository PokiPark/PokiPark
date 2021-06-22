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

public class PokedexController extends Main implements Initializable {

	ObservableList<String> pokedex_list = FXCollections.observableArrayList();

	@FXML private Label pokeInfo_Label;

	@FXML private Label pokedexEntry_Label;

	@FXML private ImageView poke_ImageView;
	
	@FXML private ListView<String> pokedex_listview;

	@FXML private void mainmenuClicked(ActionEvent event) throws IOException {
		changeStageTo(event, "MainMenu");
	}

	@FXML private void open_PokeInfo_ListViewClicked(MouseEvent event) {
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
				} catch (NullPointerException e){
					e.printStackTrace();
				}
				pokeFound = true;
			}
		}
	}

	@FXML private void sortId_Pokedex_ButtonClicked(ActionEvent event) throws SQLException {

	}
	
	@FXML private void sortAtoZ_Pokedex_ButtonClicked(ActionEvent event) {
		
	}
	
	@FXML private void sortTYP_Pokedex_ButtonClicked(ActionEvent event) {
		
	}

	@Override public void initialize(URL url, ResourceBundle rb) {
		try {
			Database.initData("pokedex");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			load_Pokedex_ListView();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void load_Pokedex_ListView() throws SQLException {
		pokedex_list.removeAll(pokedex_list);

		Database.getPokedex().forEach(pp -> {
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
                    littlePoke_ImageView.setImage(new Image("Vulpix.png"));
                    littlePoke_ImageView.setFitHeight(14);
                    littlePoke_ImageView.setFitWidth(14);

                    System.out.println(name + ".png");
                    
                    setText(name);
                    setGraphic(littlePoke_ImageView);
                }
            }
        });
	}
}