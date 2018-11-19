package br.com.unoesc.veterinaria.dialogs;

import java.io.IOException;

import br.com.unoesc.veterinaria.controller.cadastro.ControllerCadastroFilial;
import br.com.unoesc.veterinaria.model.Animais;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FilialDialogFactory {

	private Stage stageDono;

	private Animais animaisDevolta;

	public FilialDialogFactory(Stage stage) {
		this.stageDono = stage;
	}

	public boolean showDialog() {
		boolean clicandoSalvar = false;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/cadastro/CadastroFilial.fxml"));
		try {
			AnchorPane filialDialog = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Nova Filial");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(stageDono);
			Scene scene = new Scene(filialDialog);
			dialogStage.setScene(scene);

			ControllerCadastroFilial controller = loader.getController();
			controller.setStageDialog(dialogStage);
			dialogStage.showAndWait();

			clicandoSalvar = controller.clicadoSalvar();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return clicandoSalvar;
	}

	public Animais retornaAnimais() {
		return animaisDevolta;
	}
}
