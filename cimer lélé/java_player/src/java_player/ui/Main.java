package java_player.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public void start(Stage stage) {
		Lecteur l = new Lecteur();
		stage.setTitle("Player");
		stage.setScene(new Scene(l));
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
