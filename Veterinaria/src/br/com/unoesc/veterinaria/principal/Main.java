package br.com.unoesc.veterinaria.principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static BorderPane root;

	@Override
	public void start(Stage primaryStage) {

		try {
			root = (BorderPane) FXMLLoader
					.load(getClass().getResource("/br/com/unoesc/veterinaria/fxml/PaginaInicial.fxml"));
			Scene scene = new Scene(root, 1200, 800);
			scene.getStylesheets().add(
					getClass().getResource("/br/com/unoesc/veterinaria/principal/application.css").toExternalForm());
			primaryStage.setTitle("Veterinaria PRK");
			primaryStage.setScene(scene);
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/Login.fxml"));
			AnchorPane agenciaView = (AnchorPane) loader.load();
			root.setCenter(agenciaView);
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
