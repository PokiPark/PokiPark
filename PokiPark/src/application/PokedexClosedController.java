package application;

import java.io.*;

import javafx.event.*;

import javafx.fxml.*;

public class PokedexClosedController extends PokedexController {

	@FXML 
	private void pokedexClicked(ActionEvent event) throws IOException {
		//loadPokedexListView(pokedex_listview);
		changeStageTo(event, "Pokedex");
	}
	
	@FXML
	public void mainmenuClicked(ActionEvent event) throws IOException {
		changeStageTo(event, "MainMenu");
	}
}