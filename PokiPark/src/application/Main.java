package application;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.fxml.*;

public class Main extends Application {
	
	private AnchorPane root;
	private Scene scene;
	
	@Override public void start(Stage primaryStage) {
		
		try {
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("Login.fxml"));
			
			scene = new Scene(root, 600, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("PokiPark");
			primaryStage.getIcons().add(new Image("PokiPark_Logo.png"));
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}