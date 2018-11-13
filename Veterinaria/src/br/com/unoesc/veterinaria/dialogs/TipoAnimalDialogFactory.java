package br.com.unoesc.veterinaria.dialogs;

import java.io.IOException;

import br.com.unoesc.veterinaria.controller.cadastro.ControllerCadastroTipoAnimal;
import br.com.unoesc.veterinaria.model.TipoAnimal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TipoAnimalDialogFactory {

	private Stage stageDono;

	private TipoAnimal tipoAnimalDevolta;

	public TipoAnimalDialogFactory(Stage stage) {
		this.stageDono = stage;
	}

	public boolean showDialog() {
		boolean clicandoSalvar = false;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/cadastro/CadastroTipoAnimal.fxml"));
		try {
			AnchorPane TipoAnimalDialog = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Novo Tipo Do Animal");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(stageDono);
			Scene scene = new Scene(TipoAnimalDialog);
			dialogStage.setScene(scene);

			ControllerCadastroTipoAnimal controller = loader.getController();
			controller.setDialogStage(dialogStage);
			dialogStage.showAndWait();

			clicandoSalvar = controller.clicadoSalvar();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return clicandoSalvar;
	}

	public TipoAnimal retornaTipoAnimal() {
		return tipoAnimalDevolta;
	}
}
