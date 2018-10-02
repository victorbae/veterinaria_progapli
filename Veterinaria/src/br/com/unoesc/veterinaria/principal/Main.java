package br.com.unoesc.veterinaria.principal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.setProperty("tipoPersistencia", TipoPersistencia.BANCO.name());

		// System.setProperty("ambiente", TipoBanco.DESENVOLVIMENTO.name());

		if (args.length > 0) {
			System.setProperty("ambiente", args[0]);
		}

		launch(args);

	}
}
