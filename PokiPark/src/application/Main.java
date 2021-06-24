package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.fxml.*;

public class Main extends Application {
	
	@Override public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root, 600, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("PokiPark | Login");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeStageTo(ActionEvent event, String stage_name) throws IOException {
		Node source = (Node) event.getSource();
		Stage oldStage = (Stage) source.getScene().getWindow();
		oldStage.close();

		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(stage_name + ".fxml"));
		Parent root = (Parent) fxmlloader.load();
		Stage stage = new Stage();
		stage.setTitle("PokiPark | " + stage_name);
		stage.setScene(new Scene(root));
		stage.show();
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}