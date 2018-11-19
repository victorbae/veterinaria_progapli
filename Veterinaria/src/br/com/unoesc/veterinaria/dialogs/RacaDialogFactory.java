package br.com.unoesc.veterinaria.dialogs;

import java.io.IOException;

import br.com.unoesc.veterinaria.controller.cadastro.ControllerCadastroRaca;
import br.com.unoesc.veterinaria.model.Raca;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RacaDialogFactory {

	private Stage stageDono;

	private Raca racaDevolta;

	public RacaDialogFactory(Stage stage) {
		this.stageDono = stage;
	}

	public boolean showDialog() {
		boolean clicandoSalvar = false;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/cadastro/CadastroRaca.fxml"));
		try {
			AnchorPane racaDialog = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image("/br/com/unoesc/veterinaria/img/dog.png"));
			dialogStage.setTitle("Ra�as");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(stageDono);
			Scene scene = new Scene(racaDialog);
			dialogStage.setScene(scene);

			ControllerCadastroRaca controller = loader.getController();
			controller.setDialogStage(dialogStage);
			dialogStage.showAndWait();

			clicandoSalvar = controller.clicadoSalvar();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return clicandoSalvar;
	}

	public Raca retornaRaca() {
		return racaDevolta;
	}
}
