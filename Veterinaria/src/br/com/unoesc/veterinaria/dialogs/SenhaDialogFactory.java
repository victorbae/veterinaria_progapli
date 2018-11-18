package br.com.unoesc.veterinaria.dialogs;

import java.io.IOException;

import br.com.unoesc.veterinaria.controller.cadastro.ControllerRedefinirSenha;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SenhaDialogFactory {

	private Stage stageDono;

	public SenhaDialogFactory(Stage stage) {
		this.stageDono = stage;
	}

	public boolean showDialog() {
		boolean clicandoEnviar = false;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/cadastro/RedefinirSenha.fxml"));
		try {
			AnchorPane produtoDialog = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Redefinir Senha");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(stageDono);
			Scene scene = new Scene(produtoDialog);
			dialogStage.setScene(scene);

			ControllerRedefinirSenha controller = loader.getController();
			controller.setStageDialog(dialogStage);
			dialogStage.showAndWait();

			clicandoEnviar = controller.clicadoEnviar();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return clicandoEnviar;
	}
}